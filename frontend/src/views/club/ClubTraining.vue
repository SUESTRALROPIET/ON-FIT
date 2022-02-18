<template>
  <!--
    입장하면 mounted로 joinSession 실행 -> session 생성 -> webRTC가능
    leaveSession 버튼 클릭시 Club.vue로 이동
  -->
  <div id="main-container" class="container">
      <div v-if="session">
        <div class="d-flex flex-column">
          <div>
            <ClubTrainingExRecord
              :ClubInfo="ClubInfo"
              :videoEnabled="videoEnabled"
              :audioEnabled="audioEnabled"
              :isStart="isStart"
              @click-start-btn="clickStartBtn"
              @leave-session="leaveSession"
              @video-on-and-off="videoOnAndOff()"
              @audio-on-and-off="audioOnAndOff()"
            />
          </div>
          <div id="session" class="mt-13">
            <!-- <div id="main-video" class="col-md-6">
              여기가 큰거 stream-manager 기준으로 화면 바뀜
              <user-video :stream-manager="mainStreamManager" />
            </div> -->
            <!-- 작은 화면 -->
            <v-row id="video-container" class="d-flex flex-wrap">
              <!-- 내꺼 작은 화면 -->
              <v-col cols="4" class="d-flex justify-center align-center">
                <user-video
                  style="max-width: 100%"
                  :stream-manager="publisher"
                  @click.native="updateMainVideoStreamManager(publisher)"
                />
              </v-col>
              <!-- 여기가 나 제외 다른사람들 작은 화면 -->
              <v-col
                cols="4"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                class="d-flex justify-center align-center"
              >
                <user-video
                  style="max-width: 100%"
                  :stream-manager="sub"
                  @click.native="updateMainVideoStreamManager(sub)"
                />
              </v-col>
            </v-row>
          </div>
          <!-- 음소거, 화면 공유 버튼 -->
          <!-- <div>
            <button type="button" @click="videoOnAndOff()">VIDEO BUTTON</button> <br>
            <button type="button" @click="audioOnAndOff()">AUDIO BUTTON</button> <br>
            <button type="button" @click="changName()">CHANGE NAME</button>
          </div> -->
        </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import Vuex, { mapGetters } from 'vuex';
// import UserVideo from './components/UserVideo.vue';
import UserVideo from '@/views/openvidu/components/UserVideo.vue';

import ClubTrainingExRecord from '@/views/club/components/ClubTrainingExRecord.vue';

axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://i6b110.p.ssafy.io';
axios.defaults.headers.post['Content-Type'] = 'application/json';

Vue.use(Vuex);
const userStore = 'userStore';

const OPENVIDU_SERVER_URL = 'https://i6b110.p.ssafy.io:4443'; // location.hostname -> aws 도메인 주소로 변경하기.
// const OPENVIDU_SERVER_URL = `https://${window.location.hostname}:4443`; // location.hostname -> aws 도메인 주소로 변경하기.
const OPENVIDU_SERVER_SECRET = 'MY_SECRET';

export default {
  name: 'ClubTraining',
  components: {
    ClubTrainingExRecord,
    UserVideo,
  },
  data() {
    return {
      ClubInfo: {},
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      videoEnabled: true,
      audioEnabled: true,

      mySessionId: 'Session',
      myUserName: '',

      isStart: false,
    };/* mySessionId은 부모 컴포넌트한테 받은거 사용하기, myUserName은 user 정보 사용하기. */
  },
  created() {
    this.ClubInfo = this.$route.query.ClubInfo;
    this.mySessionId += this.ClubInfo.clubInfo.id;
    this.myUserName = this.getUserId();
  },
  mounted() {
    this.joinSession();
    // this.mySessionId += this.ClubInfo.clubInfo.id;
    // this.myUserName = this.getUserId();
  },
  methods: {
    ...mapGetters(userStore, [
      'getUserId',
    ]),
    /* join 버튼 클릭 이벤트 */
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      // 이 session으로 화면 전환
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        console.log(`룰루랄라${stream.connection.data}`);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });

      // Receiver of the message (usually before calling 'session.connect')

      this.session.on('signal:my-chat', (event) => {
        this.isStart = true;
        console.log(event.data); // Message
        console.log(event.from); // Connection object of the sender
        console.log(event.type); // The type of message ("my-chat")
      });

      // --- Connect to the session with a valid user token --- 여기 주의깊게 보기

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName, joong: 'jae' })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            const publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: false,
              // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true,
              // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message);
          });
      });

      window.addEventListener('beforeunload', this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener('beforeunload', this.leaveSession);

      this.$router.push({
        name: 'Club',
      });
    },

    /* 작은 화면 클릭 이벤트 -> 큰 화면 전환 */
    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`,
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`,
                )
              ) {
                window.location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },

    videoOnAndOff() {
      // eslint-disable-next-line no-undef
      this.videoEnabled = !this.videoEnabled;
      this.publisher.publishVideo(this.videoEnabled);
    },
    audioOnAndOff() {
      this.audioEnabled = !this.audioEnabled;
      this.publisher.publishAudio(this.audioEnabled);
    },
    clickStartBtn() {
      this.session.signal({
        data: 'click start Btn', // Any string (optional)
        to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
        type: 'my-chat', // The type of message (optional)
      })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>
</style>
