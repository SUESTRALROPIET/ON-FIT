<template>
  <div>
    <div class="title">
      <img :src="exTodos[order].exEng" alt="">
      <h1>운동 페이지</h1>
    </div>
    <Timer />
    <v-container>
      <v-row justify="center">
        <!-- 1. webRTC 화면 -->
        <v-col class="screen" cols="5">
          <canvas class="canvas"></canvas>
        </v-col>
        <!-- spacer -->
        <v-col cols="1"></v-col>
        <!-- 2. 운동 status -->
        <v-col class="statusBox" cols="5">
          <div class="numBox mt-4">
            <h1 class="text-center">00</h1>
          </div>
          <h1 class="mt-4 ps-10">타이머</h1>
          <h1 class="mt-4 ps-10">칼로리</h1>
          <h1 class="mt-4 ps-10">세트</h1>
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
import Timer from './components/Timer.vue';

let model; let webcam; let ctx; let labelContainer; let maxPredictions;

export default {
  name: 'PersonalTraining',
  components: {
    Timer,
  },
  data() {
    return {
      isStart: false,
      isEnd: false,
      order: 0, // 현재 운동 순서
      count: 0, // 운동 횟수
      set: 0, // 세트 횟수
    };
  },
  computed: {
    exTodos() {
      return this.$store.state.personalStore.exTodos;
    },
  },
  methods: {
    start() {
      if (!this.isStart) {
        this.isStart = true;
        this.init();
      } else {
        this.isStart = false;
      }
    },

    // teachable machine snippet
    async init() {
      const URL = `./components/teachable_machine/${this.exTodos[this.order].todoEng}/`;
      // const modelURL = URL + "model.json";
      // const metadataURL = URL + "metadata.json";
      const modelURL = `${URL}model.json`;
      const metadataURL = `${URL}metadata.json`;

      // load the model and metadata
      // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
      // Note: the pose library adds a tmPose object to your window (window.tmPose)

      model = await tmPose.load(modelURL, metadataURL);
      maxPredictions = model.getTotalClasses();

      // Convenience function to setup a webcam
      const size = 500;
      const flip = true; // whether to flip the webcam
      webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
      await webcam.setup(); // request access to the webcam
      await webcam.play();
      window.requestAnimationFrame(this.loop);

      const canvas = document.getElementById('canvas');
      canvas.width = size; canvas.height = size;
      ctx = canvas.getContext('2d');
      labelContainer = document.getElementById('label-container');
      for (let i = 0; i < maxPredictions; i += 1) { // and class labels
        labelContainer.appendChild(document.createElement('div'));
      }
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
      for (let i = 0; i < maxPredictions; i += 1) {
        const classPrediction = `${prediction[i].className}: ${prediction[i].probability.toFixed(2)}`;
        labelContainer.childNodes[i].innerHTML = classPrediction;
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
  text-align: center;
  padding-bottom: 40px;
  border: 1px solid;
}

.screen {
  height: 500px;
  border: 1px solid;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 30px;
  margin-bottom: 40px;
}
.canvas {
  display: flex;
  justify-content: center;
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
  margin-bottom: 40px;
}

.numBox {
  width: 235px;
  height: 235px;
  background: #FEE8D6;
  border-radius: 50%;
}
</style>
