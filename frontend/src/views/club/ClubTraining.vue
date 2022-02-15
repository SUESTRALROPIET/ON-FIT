<template>
<!--
  입장하면 mounted로 joinSession 실행 -> session 생성 -> webRTC가능
  leaveSession 버튼 클릭시 Club.vue로 이동
-->
<div id="main-container" class="container">
    <div v-if="session">
      <div>
        <ClubTrainingExRecord
          :ClubInfo="ClubInfo"
        />
      </div>
      <button></button>
      <div id="session">
        <div id="session-header">
          <h1 id="session-title">{{ mySessionId }}</h1>
          <input
            class="btn btn-large btn-danger"
            type="button"
            id="buttonLeaveSession"
            @click="leaveSession"
            value="Leave session"
          />
        </div>
        <div id="main-video" class="col-md-6">
          <!-- 여기가 큰거 stream-manager 기준으로 화면 바뀜 -->
          <user-video :stream-manager="mainStreamManager" />
        </div>
        <!-- 작은 화면 -->
        <div id="video-container" class="col-md-6">
          <!-- 내꺼 작은 화면 -->
          <user-video
            :stream-manager="publisher"
            @click.native="updateMainVideoStreamManager(publisher)"
          />
          <!-- 여기가 나 제외 다른사람들 작은 화면 -->
          <user-video
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click.native="updateMainVideoStreamManager(sub)"
          />
        </div>
      </div>
      <div>
        <ClubTrainingUserList/>
      </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
// import UserVideo from './components/UserVideo.vue';
import UserVideo from '@/views/openvidu/components/UserVideo.vue';

import ClubTrainingExRecord from '@/views/club/components/ClubTrainingExRecord.vue';
import ClubTrainingUserList from '@/views/club/components/ClubTrainingUserList.vue';

axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://i6b110.p.ssafy.io';
axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i6b110.p.ssafy.io:4443'; // location.hostname -> aws 도메인 주소로 변경하기.
// const OPENVIDU_SERVER_URL = `https://${window.location.hostname}:4443`; // location.hostname -> aws 도메인 주소로 변경하기.
const OPENVIDU_SERVER_SECRET = 'MY_SECRET';

export default {
  name: 'ClubTraining',
  components: {
    ClubTrainingExRecord,
    ClubTrainingUserList,
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

      mySessionId: 'SessionA',
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
    };/* mySessionId은 부모 컴포넌트한테 받은거 사용하기, myUserName은 user 정보 사용하기. */
  },
  created() {
    this.ClubInfo = this.$route.query.ClubInfo;
  },
  mounted() {
    this.joinSession();
  },
  methods: {
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
              headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-type': 'application/json',
              },
            },
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
                // location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
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
            {
              headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-type': 'application/json',
              },
            },
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
  },
};
</script>

<style>

</style>
