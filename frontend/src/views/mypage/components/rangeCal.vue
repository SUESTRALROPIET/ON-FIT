// this.calendar.dateRange => 선택된 범위 데이터 정보 담겨있음!
<template>
  <div>
    <FunctionalCalendar
      class="calendar"
      v-model="calendar"
      :is-date-range="true"
      :max-sel-days="30"
      :limits="{
        min: minDate(),
        max: maxDate()
      }"
    />
  </div>
</template>

<script>
import { FunctionalCalendar } from 'vue-functional-calendar';

export default {
  name: 'rangeCal',
  data() {
    return {
      calendar: {},
    };
  },
  components: {
    FunctionalCalendar,
  },
  methods: {
    getCurrentDay() {
      return new Date().getDate();
    },
    getCurrentMonth() {
      return new Date().getMonth() + 1;
    },
    getCurrentYear() {
      return new Date().getFullYear();
    },
    minDate() {
      return `${this.getCurrentDay()}/${this.getCurrentMonth()}/${this.getCurrentYear()}`;
    },
    maxDate() {
      if (this.getCurrentMonth() > 10) {
        return `${this.getCurrentDay()}/${this.getCurrentMonth() - 10}/${this.getCurrentYear() + 1}`;
      }
      return `${this.getCurrentDay()}/${this.getCurrentMonth() + 2}/${this.getCurrentYear()}`;
    },
  },
};
</script>

<style>

</style>
