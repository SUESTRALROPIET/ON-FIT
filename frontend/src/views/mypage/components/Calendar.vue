<template>
  <div>
    <FunctionalCalendar
      class="calendar"
      v-model="calendar"
      :marked-dates='markedDates'
      :is-date-picker="true"
    />
  </div>
</template>

<script>
import axios from 'axios';
import { FunctionalCalendar } from 'vue-functional-calendar';

export default {
  name: 'Calendar',

  components: {
    FunctionalCalendar,
  },
  data() {
    return {
      calendar: {},
      // 운동 기록이 있는 날짜
      markedDates: [
        `16/${this.getCurrentMonth()}/${this.getCurrentYear()}`,
        `18/${this.getCurrentMonth()}/${this.getCurrentYear()}`,
        `20/${this.getCurrentMonth()}/${this.getCurrentYear()}`,
        `21/${this.getCurrentMonth()}/${this.getCurrentYear()}`,
      ],
    };
  },
  created() {
    this.getExDate();
  },
  computed: {
    // 클릭된 날짜
    date() {
      return this.calendar.selectedDate;
    },
  },
  methods: {
    getCurrentMonth() {
      return new Date().getMonth() + 1;
    },
    getCurrentYear() {
      return new Date().getFullYear();
    },
    getExDate() {
      const userId = 'ssafy';
      axios.get(`${this.SERVER}/mypage/${userId}`, userId)
        .then((res) => {
          // 데이터 중 날짜만 필터링
          // TODO: 날짜 양식 바꾸기
          const result = res.data.MyExerciseLog;
          const exDate = result.map((v) => v.exTime);
          return exDate;
        });
    },
  },
};
</script>

<style>

</style>
