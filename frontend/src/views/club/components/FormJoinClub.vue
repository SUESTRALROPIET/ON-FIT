<template>
  <v-card>
    <v-card-text>
      <v-row class="d-flex justify-center align-center pa-5">
        <v-col cols="4" class="d-flex flex-column align-center mr-2">
          <div>
            <img src='@/assets/club/club_default.png' alt="club image" width="100%">
          </div>
        </v-col>
        <v-col>
          <h2 class="my-5">{{ClubInfo.clubInfo.clubName}}</h2>
          <v-row class="d-flex align-center">
            <v-col cols="3"><h3>요일</h3></v-col>
            <v-col class="d-flex">
              <v-checkbox
                v-model="ClubInfo.clubInfo.mon"
                label="월"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.tues"
                label="화"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.wedn"
                label="수"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.thur"
                label="목"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.fri"
                label="금"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.sat"
                label="토"
                disabled
              ></v-checkbox>
              <v-checkbox
                v-model="ClubInfo.clubInfo.sun"
                label="일"
                disabled
              ></v-checkbox>
            </v-col>
          </v-row>
          <v-row class="d-flex align-center">
            <v-col cols="3"><h3>시간</h3></v-col>
            <v-col><p>{{ClubInfo.clubInfo.fixTime | hhmm}}</p></v-col>
          </v-row>
          <v-row class="d-flex align-center">
            <v-col cols="3"><h3>운동기간</h3></v-col>
            <v-col>
              <p>{{ClubInfo.clubInfo.startDate | yyyyMMdd}}
                ~{{ClubInfo.clubInfo.endDate | yyyyMMdd}}</p>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn
        id="manage-club-button"
        width="30%" rounded outlined elevation="0"
        class="ma-auto my-5"
        @click="joinClub"
      >
        <span>클럽 가입하기</span>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormJoinClub',
  props: {
    ClubInfo: Object,
  },
  data() {
    return {
      time: '',
      start: '',
      end: '',
    };
  },
  methods: {
    joinClub() {
      const newClubMate = {
        clubId: 'ssafy',
        userId: this.ClubInfo.clubInfo.id,
      };
      axios.post('http://localhost:8081/club/join', newClubMate, {
        headers: {
          Authorization: 'must be change',
        },
      })
        .then((response) => {
          console.log(response);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  filters: {
    yyyyMMdd(value) {
      const newDate = new Date(value);

      const year = newDate.getFullYear();
      const month = newDate.getMonth() + 1;
      const day = newDate.getDate();

      return `${year}-${month}-${day}`;
    },
    hhmm(value) {
      const newTime = new Date(value);

      const hour = newTime.getHours();
      const min = newTime.getMinutes();

      return `${hour} : ${min}`;
    },
  },
};
</script>

<style>
</style>
