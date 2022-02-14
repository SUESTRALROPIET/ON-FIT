<template>
  <div class="d-flex recordBox">
    <v-col cols="12" class="mt-3 d-flex recordItem">
      <v-col class="ms-7 timeBox d-flex flex-column justify-center align-center" cols="4">
        <h2>06:30</h2>
        <h4>000cal</h4>
      </v-col>
      <v-col class="exBox d-flex align-center justify-space-around" cols="7">
          <h2>런지 20회</h2>
          <v-progress-circular
          :rotate="-90"
          :value="value"
          :size="60"
          :width="10"
          color="teal"
          > {{ value }}%</v-progress-circular>
      </v-col>
    </v-col>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'CalendarDetail',
  data() {
    return {
      value: 70,
      selectedDate: '15/2/2022', // 일/월/년
    };
  },
  methods: {
    getRecord() {
      const userId = 'ssafy';
      axios.get(`${this.SERVER}/mypage/${userId}`, userId)
        .then((res) => {
          // 데이터 중 선택된 날짜만 필터링
          // TODO: 날짜 양식 바꾸기
          const result = res.data.MyExerciseLog;
          const records = result.filter((v) => v.exTime === this.selectedDate);
          return records;
        });
    },
    calSuccess(count, fail) {
      return Math.floor((count / (count + fail)) * 100);
    },
  },
};
</script>

<style scoped>
.recordBox {
  background: rgb(255, 255, 255, 0.5);
}
.timeBox {
  margin-left: 10px;
  height: 77px;
  /* border: 1px solid; */
  background: #ffd2b625;
  border-radius: 30px 0 0 30px;
  padding: 0;
  margin-bottom: 30px;
}

.exBox {
  height: 77px;
  /* border: 1px solid; */
  background: #FF8F4A25;
  border-radius: 0 30px 30px 0;
  padding: 0;
  margin-bottom: 30px;
}
</style>
