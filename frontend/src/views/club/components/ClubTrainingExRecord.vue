<template>
  <v-row>
    <v-col cols="7" id="nowExbox">
      <div class="d-flex flex-column align-center">
        <v-row class="mx-3 align-center">
          <v-col cols="3"><h2>현재 운동</h2></v-col>
          <v-col cols="2">
            <img
              :src="require(`@/assets/exercise/${nowExEngName}.png`)" alt="now-ex-image"
              width="100%"
            >
          </v-col>
          <v-col cols="4" style="text-align:left;"><h2>{{nowExname}}</h2></v-col>
          <v-col cols="3"><h2>{{nowSetNum}} 세트</h2></v-col>
        </v-row>
        <div class="bar align-self-start mx-8" :style="{ width: progressValue + '%' }"></div>
      </div>
    </v-col>
    <v-col cols="4" id="record-body"
      class="d-flex flex-column justify-center pa-3 px-8"
    >
      <h3 class="mb-3">현재 기록</h3>
      <div class="text-center align-self-center" style="width: 50%">
        <h4>운동 시간</h4>
        <p>{{formattedElapsedTime}}</p>
      </div>
      <div class="d-flex justify-center">
        <v-btn
          width="30%" rounded outlined elevation="0"
          @click="exerciseStart"
        >
          시작
        </v-btn>
        <v-btn
          width="30%" rounded outlined elevation="0"
          @click="leaveSession"
        >
          종료
        </v-btn>
        <v-btn icon
          @click="videoOnAndOff"
        >
          <v-icon v-show="videoEnabled" color="green darken-2">mdi-video</v-icon>
          <v-icon v-show="!videoEnabled">mdi-video-off</v-icon>
        </v-btn>
        <v-btn icon
          @click="audioOnAndOff"
        >
          <v-icon v-show="audioEnabled" color="red darken-2">mdi-microphone</v-icon>
          <v-icon v-show="!audioEnabled">mdi-microphone-off</v-icon>
        </v-btn>
      </div>
    </v-col>
    <AlertBreakTime :showDialog="showBreakTimeDialog"/>
    <AlertFinishDialog :showDialog="showFinishDialog"/>
  </v-row>
</template>

<script>
import AlertBreakTime from '@/views/club/components/AlertBreakTime.vue';
import AlertFinishDialog from '@/views/club/components/AlertFinishDialog.vue';

import ex from '../../../../public/ex';

export default {
  name: 'ClubTrainingExRecord',
  components: {
    AlertBreakTime,
    AlertFinishDialog,
  },
  props: {
    ClubInfo: Object,
    videoEnabled: Boolean,
    audioEnabled: Boolean,
  },
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,

      showBreakTimeDialog: false,
      showFinishDialog: false,

      nowExEngName: 'boat',
      nowExname: '',
      nowSetNum: 0,
      progressValue: 0,
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(':') - 2, 8);
    },
  },
  methods: {
    leaveSession() {
      this.$emit('leave-session');
    },
    videoOnAndOff() {
      this.$emit('video-on-and-off');
    },
    audioOnAndOff() {
      this.$emit('audio-on-and-off');
    },
    /* eslint-disable no-await-in-loop */
    async exerciseStart() {
      const todayExList = this.ClubInfo.clubLog;
      for (let i = 0; i < todayExList.length;) {
        this.timeStart(); // 타이머 시작
        const nowExId = todayExList[i].exId - 1;
        const nowEx = ex.filter((v) => v.id === nowExId); // 현재 운동 id로 ex.js 파일에서 정보 가져오기
        this.nowExEngName = nowEx[0].exEng;
        this.nowExname = nowEx[0].exName; // 현재 운동명

        // this.sound(this.nowExEngName);

        const nowCount = todayExList[i].exCount; // 지정한 세트만큼 반복
        for (let j = 0; j < nowCount;) {
          this.nowSetNum += 1;
          await new Promise((resolve) => setTimeout(resolve, 30 * 1000)); // 세트는 각 30초씩 진행
          j += 1;
        }
        this.nowSetNum = 0;
        this.timeStop(); // 1세트 끝난 후 휴식 30초
        this.showBreakTimeDialog = true;
        await new Promise((resolve) => setTimeout(resolve, 30 * 1000));
        this.showBreakTimeDialog = false;
        i += 1;
      }
      this.showFinishDialog = true;
      await new Promise((resolve) => setTimeout(resolve, 1 * 1000));
      this.showFinishDialog = false;
    },
    timeStart() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
        if (this.elapsedTime % (30 * 1000) === 0) {
          this.progressValue = 0;
        }
        this.progressValue += 3.33333333333333;
      }, 1000);
    },
    timeStop() {
      clearInterval(this.timer);
    },
    sound(v) {
      const audio = new Audio(`../../../public/trainervoice/Naomi/${v}.mp3`);
      audio.play();
    },
  },
};
</script>

<style scoped>
#nowExbox {
  background-color: rgba(255, 210, 182, 1);
  border: 3px solid #FFD2B6;
  border-radius: 70px;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 3px 8px;
  text-align: center;
  margin: auto;
}
#record-body {
  border-radius: 1rem;
  background-color: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(70px) brightness(105%);
  box-shadow: rgba(0, 0, 0, 0.25) 0px 3px 8px;
}
#record-body .v-btn {
  color: black;
  background-color: white;
  margin: 0rem 0.5rem 0rem 0.5rem;
}
.bar {
  background: linear-gradient(to right, #B993D6, #8CA6DB);
  height: 10px;
  max-width: 90%;
  border-radius: 9px;
}
</style>
