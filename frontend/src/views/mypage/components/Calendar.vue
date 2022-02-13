<template>
  <div>
    <h1>캘린더 test</h1>
    <FunctionalCalendar
      class="calendar"
      v-model="calendar"
      :marked-dates='markedDates'
      :is-date-picker="true"
    />
  </div>
</template>

<script>
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
  },
};
</script>

<style>

</style>
