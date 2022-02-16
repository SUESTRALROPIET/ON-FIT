// TODO: 카메라 꺼지는지 테스트...!
<template>
  <div>
    <div class="title mx-auto">
      <h1 v-if="curEx.isDouble">{{curEx.name}}_{{dir}}</h1>
      <h1 v-else>{{curEx.name}}</h1>
    </div>
    <v-container>
      <v-row justify="center">
        <!-- 1. webRTC 화면 -->
        <v-col class="screen" cols="5">
          <div class="shell mx-auto">
            <div class="bar" :style="{ width: progress + '%' }"></div>
          </div>
          <!-- eslint-disable-next-line max-len -->
          <div><canvas id="canvas"></canvas></div>
        </v-col>
        <!-- spacer -->
        <v-col cols="1"></v-col>
        <!-- 2. 운동 status -->
        <v-col class="statusBox" cols="5">
          <div class="imgBox mx-auto mt-4">
            <img :src="require(`@/assets/exercise/${curEx.eng}.png`)" height="235px">
          </div>
          <h1 class="mt-4 text-center"> {{count}} / {{curEx.totalNum}} 회</h1>
          <h1 class="mt-4 text-center"> {{set}} / {{curEx.totalSet}} 세트</h1>
          <h1 class="mt-4 text-center">운동시간 {{formattedElapsedTime}}</h1>
        </v-col>

        <!-- 3. 버튼 -->
        <v-col class="btnBox d-flex justify-center pt-0" cols="12">
          <v-btn class="mr-6" fab dark small v-if="!isPlay" @click="start">
            <v-icon>mdi-play</v-icon>
          </v-btn>
          <v-btn class="mr-6" fab dark small v-else @click="stop">
            <v-icon>mdi-stop</v-icon>
          </v-btn>
          <v-btn fab dark small @click="clickExit">
            <v-icon>mdi-logout</v-icon>
          </v-btn>
        </v-col>
        <AlertBreakTime :showDialog="showBreakTimeDialog"/>
        <AlertFinishDialog @exit2="exit2" @exit3="exit3" :showDialog="showFinsishDialog"/>
        <AlertStopDialog @restart="start" :showDialog="showStopDialog"/>
        <AlertExitDialog @restart="start" @exit="exit" :showDialog="showExitDialog"/>
        <AlertLoadingDialog :showDialog="showLoadingDialog"/>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import '@tensorflow/tfjs';
import * as tmPose from '@teachablemachine/pose';
// import axios from 'axios';
import Vue from 'vue';
import Vuex, { mapGetters } from 'vuex';
import { apiInstance } from '@/api/index';

import AlertBreakTime from '@/views/personal/components/AlertBreakTime.vue';
import AlertFinishDialog from '@/views/personal/components/AlertFinishDialog.vue';
import AlertStopDialog from '@/views/personal/components/AlertStopDialog.vue';
import AlertExitDialog from '@/views/personal/components/AlertExitDialog.vue';
import AlertLoadingDialog from '@/views/personal/components/AlertLoadingDialog.vue';

const userStore = 'userStore';
const api = apiInstance();
let model; let webcam; let ctx;

Vue.use(Vuex);

export default {
  name: 'PersonalTraining',
  components: {
    AlertBreakTime,
    AlertFinishDialog,
    AlertStopDialog,
    AlertExitDialog,
    AlertLoadingDialog,
  },
  data() {
    return {
      isPlay: false,
      dir: '오른쪽',
      status: 'stand', // 현재 운동 상태
      cur: 0, // 현재 운동 순서
      count: 0, // 운동 횟수
      fail: 0, // 동작을 틀린 횟수
      set: 0, // 세트 횟수
      elapsedTime: 0, // timer
      timer: undefined,
      progress: 0, // progress bar
      isProgress: false,
      exTimer: undefined,
      showBreakTimeDialog: false, // dialog
      showFinsishDialog: false,
      showStopDialog: false,
      showExitDialog: false,
      showLoadingDialog: false,
    };
  },
  mounted() {
    this.init();
    this.showLoadingDialog = true;
  },
  beforeDestroy() {
    window.location.reload();
  },
  computed: {
    exTodos() {
      return this.$store.state.personalStore.exTodos;
    },
    curEx() {
      return {
        ex_id: this.exTodos[this.cur].ex_id,
        name: this.exTodos[this.cur].todoName,
        eng: this.exTodos[this.cur].todoEng,
        totalNum: this.exTodos[this.cur].todoNum,
        totalSet: this.exTodos[this.cur].todoSet,
        time: this.exTodos[this.cur].todoTime,
        isDouble: this.exTodos[this.cur].isDouble,
      };
    },
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(':') - 2, 8);
    },
    trainer() {
      return 'Naomi';
    },
  },
  methods: {
    ...mapGetters(userStore, [
      'getUserId',
    ]),
    start() {
      this.isPlay = true;
      this.timeStart();
      this.showStopDialog = false;
      this.showExitDialog = false;
    },
    stop() {
      this.timeStop();
      this.isPlay = false;
      this.showStopDialog = true;
    },
    clickExit() {
      this.showExitDialog = true;
    },
    exit() { // 운동 중 나가기
      this.save();
      const userId = this.getUserId();
      this.$store.dispatch('getExDays', userId);
      this.$store.dispatch('getTime', userId);
      this.$store.dispatch('getCal', userId);
      setTimeout(() => {
        this.$router.push({ name: 'Main' });
      }, 200);
    },
    exit2() { // 운동 후 나가기: MyPage로
      const userId = this.getUserId();
      this.$store.dispatch('getExDays', userId);
      this.$store.dispatch('getTime', userId);
      this.$store.dispatch('getCal', userId);
      setTimeout(() => {
        this.$router.push({ name: 'MyPage' });
      }, 200);
    },
    exit3() { // 운동 후 나가기: Home으로
      const userId = this.getUserId();
      this.$store.dispatch('getExDays', userId);
      this.$store.dispatch('getTime', userId);
      this.$store.dispatch('getCal', userId);
      setTimeout(() => {
        this.$router.push({ name: 'Main' });
      }, 200);
    },
    save() {
      const info = {
        userId: this.getUserId(),
        exId: this.curEx.ex_id,
        exSet: this.set,
        exDuration: this.set * this.curEx.time * this.curEx.totalNum, // 단위: sec
        exStatus: this.fail,
      };
      api.post('/personal', info);
      // axios.post('http://localhost:8081/api/personal', info, {
      //   headers: {
      //     'Access-Control-Allow-Origin': '*',
      //     'Content-type': 'application/json',
      //   },
      // });
      this.fail = 0;
    },
    sound(v) {
      const audio = new Audio(`../../../public/trainervoice/${this.trainer}/${v}.mp3`);
      audio.play();
    },
    timeStart() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    timeStop() {
      clearInterval(this.timer);
    },

    exTimerStart() {
      this.isProgress = true;
      const sec = this.curEx.eng === 'flank' ? 3.33 : 20;
      this.exTimer = setInterval(() => {
        if (this.progress >= 99) {
          this.exTimerEnd();
        } else {
          this.progress += sec;
        }
      }, 1000);
    },
    exTimerEnd() {
      clearInterval(this.exTimer);
      this.isProgress = false;
    },
    // teachable machine snippet
    async init() {
      this.showLoadingDialog = true;
      const URL = `../../../public/teachable_machine/${this.curEx.eng}/`;
      const modelURL = `${URL}model.json`;
      const metadataURL = `${URL}metadata.json`;

      // load the model and metadata
      // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
      // Note: the pose library adds a tmPose object to your window (window.tmPose)

      model = await tmPose.load(modelURL, metadataURL);

      // Convenience function to setup a webcam
      const flip = true; // whether to flip the webcam
      webcam = new tmPose.Webcam(450, 450, flip); // width, height, flip
      await webcam.setup(); // request access to the webcam
      await webcam.play();
      window.requestAnimationFrame(this.loop);

      const canvas = document.getElementById('canvas');
      canvas.width = 450; canvas.height = 450;
      ctx = canvas.getContext('2d');
    },
    // eslint-disable-next-line no-unused-vars
    async loop(timestamp) {
      if (this.showLoadingDialog) {
        this.showLoadingDialog = false;
        await this.sound('start');
        setTimeout(() => { this.sound(this.curEx.eng); }, 13500);
      }
      webcam.update(); // update the webcam frame
      await this.predict();

      window.requestAnimationFrame(this.loop);
    },

    async predict() {
      // Prediction #1: run input through posenet
      // estimatePose can take in an image, video or canvas html element
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);

      // 좌, 우 구분 X
      if (!this.curEx.isDouble && this.isPlay === true) {
        if (prediction[0].probability.toFixed(2) >= 0.7) {
          // 운동 카운트
          if (this.isProgress) {
            this.exTimerEnd();
          }
          if (this.status === this.curEx.eng && this.progress >= 99) {
            this.count += 1;
            this.sound(this.count);
            // 세트 카운트 및 운동 횟수 리셋
            if (this.count === this.curEx.totalNum) {
              this.count = 0;
              this.set += 1;
              if (this.set === this.curEx.totalSet) {
                // 휴식 & 운동 기록 저장 & 다음 운동으로 & 세트 리셋 or 종료
                this.timeStop();
                if (this.cur !== this.exTodos.length - 1) {
                  const ran1to4 = Math.floor(Math.random() * 4) + 1;
                  setTimeout(() => { this.sound(`rest_${ran1to4}`); }, 2000); // 음성 (휴식)
                  this.showBreakTimeDialog = true;
                  await new Promise((resolve) => setTimeout(resolve, 60 * 1000));
                  this.showBreakTimeDialog = false;
                  this.save();
                  this.cur += 1;
                  this.sound(this.curEx.eng);
                  this.set = 0;
                } else {
                  this.showFinsishDialog = true;
                }
              }
            }
          }
          this.progress = 0;
          this.status = 'stand';
        } else if (prediction[1].probability.toFixed(2) >= 0.85) {
          this.status = this.curEx.eng;
          if (!this.isProgress) {
            this.exTimerStart();
          }
        } else if (prediction[2].probability.toFixed(2) >= 0.5) {
          if (this.status === 'stand' || this.status === this.curEx.eng) {
            this.status = 'fail';
            this.exTimerEnd();
            this.progress = 0;
            const ran1to4 = Math.floor(Math.random() * 4) + 1;
            this.sound(`fail_${ran1to4}`);
            this.fail += 1;
          }
        }
        // 좌, 우 구분
      } else if (this.curEx.isDouble && this.isPlay === true) {
        if (prediction[0].probability.toFixed(2) >= 0.7) {
          // 운동 카운트
          if (this.status === this.curEx.eng && this.progress >= 99) {
            this.count += 1;
            this.sound(this.count);

            // 세트 카운트 및 운동 횟수 리셋
            if (this.count === this.curEx.totalNum && this.dir === '오른쪽') {
              this.count = 0;
              this.dir = '왼쪽';
            } else if (this.count === this.curEx.totalNum && this.dir === '왼쪽') {
              this.count = 0;
              this.dir = '오른쪽';
              this.set += 1;
              if (this.set === this.curEx.totalSet) {
                this.timeStop();
                if (this.cur !== this.exTodos.length - 1) {
                  const ran1to4 = Math.floor(Math.random() * 4) + 1;
                  setTimeout(() => { this.sound(`rest_${ran1to4}`); }, 2000); // 음성 (휴식)
                  this.showBreakTimeDialog = true;
                  await new Promise((resolve) => setTimeout(resolve, 60 * 1000));
                  this.showBreakTimeDialog = false;
                  this.save();
                  this.cur += 1;
                  this.sound(this.curEx.eng);
                  this.set = 0;
                } else {
                  setTimeout(() => { this.showFinsishDialog = true; }, 2000);
                }
              }
            }
          }
          this.progress = 0;
          this.status = 'stand';
        // eslint-disable-next-line max-len
        } else if (prediction[1].probability.toFixed(2) >= 0.85 || prediction[2].probability.toFixed(2) >= 0.85) {
          this.status = this.curEx.eng;
          if (!this.isProgress) {
            this.exTimerStart();
          }
        } else if (prediction[3].probability.toFixed(2) >= 0.5) {
          if (this.status === 'stand' || this.status === this.curEx.eng) {
            this.status = 'fail';
            this.exTimerEnd();
            this.progress = 0;
            const ran1to4 = Math.floor(Math.random() * 4) + 1;
            this.sound(`fail_${ran1to4}`);
            this.fail += 1;
          }
        }
      }
      // finally draw the poses
      this.drawPose(pose);
    },

    drawPose(pose) {
      if (webcam.canvas) {
        ctx.drawImage(webcam.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
          const minPartConfidence = 0.5;
          tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
          tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
        }
      }
    },
  },
};
</script>

<style scoped>
.title {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  width: 50%;
  height: 80px;
  border: 1px solid;
  background-color: rgba(255, 210, 182, 0.8);
  border: 3px solid #FFD2B6;
  border-radius: 70px;
}

.screen {
  height: 500px;
  border: 3px solid #FFD2B6;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 30px;
}

.statusBox {
  height: 500px;
  border: 3px solid #FFD2B6;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 30px;
}

.imgBox {
  width: 235px;
  height: 235px;
  background: #FEE8D6;
  border-radius: 50%;
}
.shell {
  height: 20px;
  width: 250px;
  border: 1px solid #aaa;
  border-radius: 13px;
  padding: 3px;
}

.bar {
  background: linear-gradient(to right, #B993D6, #8CA6DB);
  height: 20px;
  width: 15px;
  border-radius: 9px;
}
</style>
