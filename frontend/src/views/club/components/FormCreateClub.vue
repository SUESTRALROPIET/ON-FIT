<template>
  <v-card id="create-club-form">
    <v-card-text>
      <v-row class="my-3">
        <v-col cols="4">
          <img
            :src="imagePreviewURL" alt="club image"
            width="100%"
          >
        </v-col>
        <v-col cols="7" class="align-self-end">
          <v-file-input
            accept="image/*"
            label="Club Image"
            @change="onImageFileChange"
          ></v-file-input>
          <v-text-field
            v-model="clubName"
            label="클럽명*"
            required
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="3" class="text-center">
          <h3>요일</h3>
        </v-col>
        <v-col cols="9" class="pl-0">
          <div class="d-flex flex-wrap">
            <v-checkbox
              v-model="checkboxMon"
              dense
              label="월"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxTues"
              dense
              label="화"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxWedn"
              dense
              label="수"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxThur"
              dense
              label="목"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxFri"
              dense
              label="금"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxSat"
              dense
              label="토"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
            <v-checkbox
              v-model="checkboxSun"
              dense
              label="일"
              color="success"
              class="ma-0 pa-0 mr-3"
            ></v-checkbox>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="3"  class="text-center align-self-center">
          <h3>시간</h3>
        </v-col>
        <v-col cols="8" class="pl-0">
          <v-menu
            ref="menu"
            v-model="timePicker"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="time"
            transition="scale-transition"
            offset-y
            max-width="290px"
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="time"
                label="Pick Time"
                prepend-icon="mdi-clock-time-four-outline"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-time-picker
              v-if="timePicker"
              v-model="time"
              full-width
              @click:minute="$refs.menu.save(time)"
            ></v-time-picker>
          </v-menu>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="3"  class="text-center align-self-center">
          <h3>운동기간</h3>
        </v-col>
        <v-col cols="8" class="pl-0">
          <v-menu
            v-model="datePicker"
            :close-on-content-click="false"
            :nudge-right="40"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateRangeText"
                label="Pick Date"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="dates"
              v-show="dates.length <= 2"
              range
            ></v-date-picker>
          </v-menu>
        </v-col>
      </v-row>
    </v-card-text>
    <v-card-actions>
      <v-btn
        id="create-club-button"
        width="30%"
        rounded
        outlined
        elevation="0"
        class="ma-auto mt-5"
        @click="createClub"
      >
        <span>클럽 만들기</span>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import axios from 'axios';

export default {
  name: 'FormCreateClub',
  data() {
    return {
      timePicker: false,
      datePicker: false,
      imagePreviewURL: require('@/assets/club/club_default.png'),
      clubName: '',
      checkboxMon: false,
      checkboxTues: false,
      checkboxWedn: false,
      checkboxThur: false,
      checkboxFri: false,
      checkboxSat: false,
      checkboxSun: false,
      time: null,
      dates: [],

      LogginedUser: '현재로그인한사용자',
    };
  },
  props: {
    value: Boolean,
  },
  computed: {
    show: {
      get() {
        return this.value;
      },
    },
    dateRangeText() {
      return this.dates.join(' ~ ');
    },
  },
  methods: {
    onImageFileChange(payload) {
      const defaultImage = require('@/assets/club/club_default.png');
      const file = payload;
      if (file) {
        this.imagePreviewURL = URL.createObjectURL(file);
        URL.revokeObjectURL(file);
      } else {
        this.imagePreviewURL = defaultImage;
      }
    },
    createClub() {
      const newClubInfo = {
        clubId: {
          clubImg: this.imagePreviewURL,
          clubName: this.clubName,
          manager: this.LogginedUser,
          mon: this.checkboxMon,
          tues: this.checkboxTues,
          wedn: this.checkboxWedn,
          thur: this.checkboxThur,
          fri: this.checkboxFri,
          sat: this.checkboxSat,
          sun: this.checkboxSun,
          startDate: this.dates[0],
          endDate: this.dates[1],
          fixTime: this.time,
          finish: false,
        },
        clubLogs: [],
      };
      axios.post('http://localhost:8081/club/', newClubInfo, {
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
      this.clearAll();
    },
    clearAll() {
      this.imagePreviewURL = require('@/assets/club/club_default.png');
      this.clubName = '';
      this.checkboxMon = false;
      this.checkboxTues = false;
      this.checkboxWedn = false;
      this.checkboxThur = false;
      this.checkboxFri = false;
      this.checkboxSat = false;
      this.checkboxSun = false;
      this.time = null;
      this.dates = [];
    },
  },
};
</script>
<style scoped>
#create-club-form {
  padding: 2rem;
  border-radius: 1rem;
}
#create-club-button {
  color: black;
  background-color: white;
}
</style>
