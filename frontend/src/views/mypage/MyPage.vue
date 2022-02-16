<template>
  <div
    class="d-flex flex-column mx-auto"
  >
    <v-row>
      <v-col cols="5" class="mr-5">
        <div class="d-flex">
          <v-avatar
            class="mr-5 align-self-center"
            color="orange"
            size="60"
          >
            <span class="white--text text-h5">{{user_info.full_name[0]}}</span>
          </v-avatar>
          <div class="d-flex flex-column">
            <v-row>
              <h1>{{user_info.full_name}} 님</h1>
            </v-row>
            <v-row class="d-flex">
              <v-col cols="3" class="mx-2">
                <ButtonClubs/>
              </v-col>
              <v-col cols="3" class="mx-2">
                <ButtonNickname
                  :user_info="user_info"
                />
              </v-col>
              <v-col cols="3" class="mx-2">
                <ButtonDeleteUser/>
              </v-col>
            </v-row>
          </div>
        </div>
        <div id="mypage-record"
          class="d-flex flex-column my-5"
        >
          <div class="d-flex justify-space-between">
            <h4>운동시간</h4>
            <h2>00 h 00 m</h2>
          </div>
          <div class="d-flex justify-space-between">
            <h4>칼로리</h4>
            <h2>0,000 kcal</h2>
          </div>
        </div>
      </v-col>
      <v-col class="ms-10" cols="6">
        <ExRecord/>
      </v-col>
    </v-row>
    <v-row id="mypage-bottom">
      <v-col cols="5">
        <Calendar />
      </v-col>
      <v-col class="ms-10" cols="6">
        <CalendarDetail />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios';
import ButtonClubs from '@/views/mypage/components/ButtonClubs.vue';
import ButtonNickname from '@/views/mypage/components/ButtonNickname.vue';
import ButtonDeleteUser from '@/views/mypage/components/ButtonDeleteUser.vue';
import ExRecord from '@/views/mypage/components/ExRecord.vue';
import Calendar from '@/views/mypage/components/Calendar.vue';
import CalendarDetail from '@/views/mypage/components/CalendarDetail.vue';
import { apiInstance } from '@/api/index';

const api = apiInstance();

export default {
  name: 'MyPage',
  components: {
    ButtonClubs,
    ButtonNickname,
    ButtonDeleteUser,
    ExRecord,
    Calendar,
    CalendarDetail,
  },
  data() {
    return {
      SERVER: 'http://localhost:8081',
      user_info: {
        user_email: 'abc@ssafy.com',
        full_name: '김아무개',
        created_at: '1010-10-10',
        trainer_code: 1,
        monthlyCal: 100,
        monthlyTime: 100,
      },
      exLog: [
        {
          id: 3,
          exId: { // 어떤 운동인지
            id: 1,
            kind: 'tree',
            count: 12,
            calorie: 5,
            hibernateLazyInitializer: {},
          },
          exCount: 120, // 현재 운동 총 횟수
          exDuration: 30, // 운동시간
          exStatus: 0, // 실패횟수
          exCal: 50, // 칼로리 소모량
          exTime: '2022-02-11T16:49:23.266+00:00', // 시간
        },
        {
          id: 4,
          exId: {
            id: 1,
            kind: 'tree',
            count: 12,
            calorie: 5,
            hibernateLazyInitializer: {},
          },
          exCount: 120,
          exDuration: 30,
          exStatus: 1,
          exCal: 50,
          exTime: '2022-02-11T16:53:29.619+00:00',
        },
      ],
    };
  },
  created() {
    this.getExLog();
    this.read();
  },
  computed: {
    test() {
      console.log(this.exLog.exTime);
      return 'test';
    },
  },
  methods: {
    read() {
      api.get('/hello').then((res) => {
        alert(res.data);
      });
    },
    getExLog() {
      const userId = 'ssafy';
      axios.get(`${this.SERVER}/mypage/${userId}`, userId)
        .then((res) => {
          // 데이터 중 날짜만 필터링
          console.log(res.data.MyExerciseLog);
        });
    },
  },
};
</script>

<style scoped>
#mypage-record div{
  padding: 1rem 0rem;
}
</style>
