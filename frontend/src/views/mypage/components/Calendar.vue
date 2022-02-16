// TODO: selectedDate 확인
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
      calendar: {
        currentDate: new Date(),
      },
      markedDates: [
      ],
      month: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      len: [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
      calData: undefined,
      timeData: undefined,
    };
  },
  // TODO: API test
  beforeCreate() {
    console.log('beforeCreate');
    this.markedDates = this.$store.state.accountsStore.exDays;
    console.log(this.markedDates);
  },
  computed: {
    // TODO: emit 잘 되는지 체크
    currentDate() {
      console.log('여기가 문젠가');
      if (Object.keys(this.calendar)) {
        console.log('통과?');
        const result1 = String(this.calendar.currentDate).split(' ');
        const monthInt = this.month.indexOf(result1[1]) + 1;
        const monthStr = monthInt < 10 ? `0${monthInt}` : monthInt;
        const result2 = `${result1[3]}-${monthStr}`;
        console.log(result2);
        this.$emit('current', result2);
        return result2;
      }
      const result1 = String(new Date()).split(' ');
      const monthInt = this.month.indexOf(result1[1]) + 1;
      const monthStr = monthInt < 10 ? `0${monthInt}` : monthInt;
      const result2 = `${result1[3]}-${monthStr}`;
      console.log(result2);
      this.$emit('current', result2);
      return result2;
    },
    selected() {
      const selected = this.calendar.selectedDate;
      this.$emit('selected', selected);
      return selected;
    },
  },
  methods: {
    getCurrentMonth() {
      return new Date().getMonth() + 1;
    },
    getCurrentYear() {
      return new Date().getFullYear();
    },
    convertDate(dateTime) {
      const result1 = dateTime.split('-');
      const result2 = result1.split('T');
      const ddmmyyyy = `${result2[0]}/${result1[2]}/${result1[0]}`;
      return ddmmyyyy;
    },
    getMonthlyLog() {
      // TODO: change userId
      const userId = 1;
      axios.get(`http://localhost:8081/mypage/${userId}`)
        .then((res) => {
          // eslint-disable-next-line max-len
          const monthlyLog = res.data.MyExerciseLog.filter((v) => v.exTime.includes(this.currentDate));
          // 결과 저장할 배열
          const month = this.currentDate.substr(5);
          const leng = this.len.at(month);
          this.timeData = new Array(leng).fill(0);
          this.calData = new Array(leng).fill(0);
          monthlyLog.forEach((v) => {
            const day = v.exTime.substr(8, 2) - 1;
            this.timeData[day] += v.exDuration;
            this.calData[day] += v.exCal;
          });
        });
    },
    // test용
    // checkMonthlyLog() {
    //   const month = this.currentDate.substr(5);
    //   const leng = this.len.at(month);
    //   // 배열 생성 및 초기화
    //   this.timeData = new Array(leng).fill(0);
    //   this.calData = new Array(leng).fill(0);
    //   const monthlyLog = MyExerciseLog.filter((v) => v.exTime.includes(this.currentDate));
    //   monthlyLog.forEach((v) => {
    //     const day = v.exTime.substr(8, 2) - 1;
    //     this.timeData[day] += v.exDuration;
    //     this.calData[day] += v.exCal;
    //   });
    // },
  },
};
</script>

<style>

</style>
