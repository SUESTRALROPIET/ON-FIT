<template>
  <div>
    <div
      id="record-body"
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
          @click="start"
        >
          시작
        </v-btn>
        <v-btn
          width="40%"
          rounded
          outlined
          elevation="0"
          @click="stop"
        >
          시작
        </v-btn>
        <v-btn
          width="40%"
          rounded
          outlined
          elevation="0"
          @click="reset"
        >
          시작
        </v-btn>
        <div class="mx-2">
        </div>
        <ManageClubButton
          :ClubInfo="ClubInfo"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ManageClubButton from '@/views/club/components/ManageClubButton.vue';

export default {
  name: 'ClubTrainingExRecord',
  components: {
    ManageClubButton,
  },
  props: {
    ClubInfo: Object,
  },
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
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
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    stop() {
      clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
    },
  },
};
</script>

<style scoped>
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
