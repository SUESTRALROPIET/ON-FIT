<template>
  <v-row>
    <v-col cols="7" id="nowExbox">
      <v-row class="align-center mx-3">
        <v-col cols="3"><h2>현재 운동</h2></v-col>
        <v-col cols="2">
          <img :src="require(`@/assets/exercise/boat.png`)" alt="" width="100%">
        </v-col>
        <v-col cols="4" style="text-align:left;"><h2>런지{{nowExname}}</h2></v-col>
        <v-col cols="3"><h2>1회</h2></v-col>
      </v-row>
    </v-col>
    <v-col cols="4" id="record-body"
      class="d-flex flex-column justify-center pa-3 px-8"
    >
      <h3 class="mb-3">현재 기록</h3>
      <div
        class="d-flex"
      >
        <div class="text-center" style="width: 50%">
          <h4>운동 시간</h4>
          <p>{{formattedElapsedTime}}</p>
        </div>
        <v-divider vertical></v-divider>
        <div class="text-center" style="width: 50%">
          <h4>소비 칼로리</h4>
          <p><span>0</span> 칼로리</p>
        </div>
      </div>
      <div
        class="d-flex justify-center"
      >
        <v-btn
          width="40%"
          rounded
          outlined
          elevation="0"
          @click="exerciseStart"
        >
          시작
        </v-btn>
        <div class="mx-2">
        </div>
        <ManageClubButton
          :ClubInfo="ClubInfo"
        />
      </div>
    </v-col>
    <AlertBreakTime :showDialog="showBreakTimeDialog"/>
    <AlertFinishDialog :showDialog="showFinishDialog"/>
  </v-row>
</template>

<script>
import ManageClubButton from '@/views/club/components/ManageClubButton.vue';
import AlertBreakTime from '@/views/club/components/AlertBreakTime.vue';
import AlertFinishDialog from '@/views/club/components/AlertFinishDialog.vue';

export default {
  name: 'ClubTrainingExRecord',
  components: {
    ManageClubButton,
    AlertBreakTime,
    AlertFinishDialog,
  },
  props: {
    ClubInfo: Object,
  },
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,

      showBreakTimeDialog: false,
      showFinishDialog: false,
      nowExname: '',

      exList: [
        {
          ex_id: 1,
          count: 5,
        },
        {
          ex_id: 2,
          count: 5,
        },
        {
          ex_id: 3,
          count: 5,
        },
      ],
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
    /* eslint-disable no-await-in-loop */
    async exerciseStart() {
      const todayExList = this.exList;
      for (let i = 0; i < todayExList.length;) {
        this.timeStart();
        const exTime = todayExList[i].count;
        this.nowExname = todayExList[i].ex_id;
        await new Promise((resolve) => setTimeout(resolve, exTime * 1000));
        this.timeStop();
        this.showBreakTimeDialog = true;
        await new Promise((resolve) => setTimeout(resolve, 60 * 1000));
        this.showBreakTimeDialog = false;
        i += 1;
      }
      this.showFinishDialog = true;
      await new Promise((resolve) => setTimeout(resolve, 5 * 1000));
      this.showFinishDialog = false;
    },
    timeStart() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    timeStop() {
      clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
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
}
</style>
