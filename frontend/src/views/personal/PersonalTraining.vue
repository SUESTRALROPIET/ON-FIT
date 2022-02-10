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
          <canvas class="canvas"></canvas>
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
          <v-btn
            class="mr-6"
            fab
            dark
            small
            @click="start"
          >
            <v-icon v-if="isStart">mdi-stop</v-icon>
            <v-icon v-else>mdi-play</v-icon>
          </v-btn>
          <v-btn
            fab
            dark
            small
          >
            <v-icon>mdi-logout</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import '@tensorflow/tfjs';
import * as tmPose from '@teachablemachine/pose';

let model; let webcam; let ctx;

export default {
  name: 'PersonalTraining',
  components: {
  },
  data() {
    return {
      isStart: false,
      isEnd: false,
      dir: '왼쪽',
      status: 'stand', // 현재 운동 상태
      cur: 0, // 현재 운동 순서
      count: 0, // 운동 횟수
      fail: 0, // 동작을 틀린 횟수 (정확도용_예비)
      set: 0, // 세트 횟수
      elapsedTime: 0,
      timer: undefined,
      progress: 0,
      exTimer: undefined,
    };
  },
  computed: {
    exTodos() {
      return this.$store.state.personalStore.exTodos;
    },
    curEx() {
      return {
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
  },
  methods: {
    start() {
      if (!this.isStart) {
        this.isStart = true;
        this.timerStart();
        this.init();
        this.exTimerStart();
      } else {
        this.isStart = false;
        this.timerStop();
      }
    },
    end() {
      // 운동 결과 보여주기 or 운동 종료 팝업
    },
    exit() {
      // 나가시겠습니까 팝업
    },
    rest() {

    },
    timerStart() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    timerStop() {
      clearInterval(this.timer);
    },

    exTimerStart() {
      const sec = this.curEx.eng === 'flank' ? 3.33 : 20;
      this.timer = setInterval(() => {
        if (this.progress >= 99) {
          this.exTimerEnd();
        } else {
          this.progress += sec;
        }
      }, 1000);
    },
    exTimerEnd() {
      clearInterval(this.exTimer);
    },
    // teachable machine snippet
    async init() {
      const URL = `./components/teachable_machine/${this.curEx.eng}/`;
      // const modelURL = URL + "model.json";
      // const metadataURL = URL + "metadata.json";
      const modelURL = `${URL}model.json`;
      const metadataURL = `${URL}metadata.json`;

      // load the model and metadata
      // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
      // Note: the pose library adds a tmPose object to your window (window.tmPose)

      model = await tmPose.load(modelURL, metadataURL);

      // Convenience function to setup a webcam
      const size = 450;
      const flip = true; // whether to flip the webcam
      webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
      await webcam.setup(); // request access to the webcam
      await webcam.play();
      window.requestAnimationFrame(this.loop);

      const canvas = document.getElementById('canvas');
      canvas.width = size; canvas.height = size;
      ctx = canvas.getContext('2d');
    },
    // eslint-disable-next-line no-unused-vars
    async loop(timestamp) {
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
      if (!this.curEx.isDouble) {
        if (prediction[0].probability.toFixed(2) >= 0.7) {
          // 운동 카운트
          if (this.status === this.curEx.name && this.progress >= 99) {
            this.count += 1;
            // 음성 호출하기
            // const audio = new Audio('음성파일명');
            // audio.play();

            // 세트 카운트 및 운동 횟수 리셋
            if (this.count === this.curEx.totalNum) {
              this.count = 0;
              this.set += 1;
              // 휴식....?
              if (this.set === this.curEx.totalSet) {
                // 휴식 주기
                // fail 횟수 저장하기 (local)
                // 세트 리셋
                // 다음 운동으로 넘어가기 or 종료
                if (this.cur !== this.exTodos.length) {
                  this.cur += 1;
                } else {
                  this.end();
                }
              }
            }
          }
          this.progress = 0;
          this.status = 'stand';
        } else if (prediction[1].probability.toFixed(2) >= 0.85) {
          this.exTimerStart();
          this.status = this.curEx.name;
        } else if (prediction[2].probability.toFixed(2) >= 0.5) {
          if (this.status === 'stand' || this.status === this.curEx.name) {
            this.fail += 1;
            // 음성 호출하기
            // const audio = new Audio('음성파일명');
            // audio.play();
          }
          this.progress = 0;
          this.status = 'fail';
        }
      }
      // 좌, 우 구분
      if (this.curEx.isDouble) {
        if (prediction[0].probability.toFixed(2) >= 0.7) {
          // 운동 카운트
          if (this.status === this.curEx.name && this.progress >= 99) {
            this.count += 1;
            // 음성 호출하기
            // const audio = new Audio('음성파일명');
            // audio.play();

            // 세트 카운트 및 운동 횟수 리셋
            if (this.count === this.curEx.totalNum && this.dir === '왼쪽') {
              this.count = 0;
              this.dir = '오른쪽';
            } else if (this.count === this.curEx.totalNum && this.dir === '오른쪽') {
              this.count = 0;
              this.dir = '왼쪽';
              this.set += 1;
              if (this.set === this.curEx.totalSet) {
                // 휴식 주기
                // fail 횟수 저장하기 (local)
                // 세트 리셋
                // 다음 운동으로 넘어가기 or 종료
                if (this.cur !== this.exTodos.length) {
                  this.cur += 1;
                } else {
                  this.end();
                }
              }
            }
            // 휴식....?
          }
          this.progress = 0;
          this.status = 'stand';
        // eslint-disable-next-line max-len
        } else if (prediction[1].probability.toFixed(2) >= 0.85 || prediction[2].probability.toFixed(2) >= 0.85) {
          this.exTimerStart();
          this.status = this.curEx.name;
        } else if (prediction[3].probability.toFixed(2) >= 0.5) {
          if (this.status === 'stand' || this.status === this.curEx.name) {
            this.fail += 1;
            // 음성 호출하기
            // const audio = new Audio('음성파일명');
            // audio.play();
          }
          this.progress = 0;
          this.status = 'fail';
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
  background-color: rgba(255, 210, 182, 1);
  border: 3px solid #FFD2B6;
  border-radius: 70px;
}

.screen {
  height: 500px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 30px;
}
.canvas {
  width: 450px;
  height: 450px;
  border: 1px solid;
  background: rgb(0, 0, 0, 0.5);
}
.statusBox {
  height: 500px;
  border: 1px solid;
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
  span {
    float: right;
    padding: 4px 5px;
    color: #fff;
    font-size: 0.7em
  }
}
</style>
