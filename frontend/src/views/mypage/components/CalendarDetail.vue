<template>
  <div class="d-flex">
    <v-col
      class="d-flex"
      cols="12"
    >
      <v-col class="ms-7 timeBox d-flex flex-column justify-center align-center" cols="3">
        <h2>{{ convertTime(record.exDuration) }}</h2>
        <h4>{{ record.exCal }} kcal</h4>
      </v-col>
      <v-col class="exBox d-flex align-center justify-space-around" cols="8">
        <h2>{{ex[record.exId.id].exName}} {{ record.exCount }}회</h2>
        <v-progress-circular
        :rotate="-90"
        :value="calSuccess(record.exCount, record.exStatus)"
        :size="60"
        :width="10"
        color="teal"
        > {{ calSuccess(record.exCount, record.exStatus) }}%</v-progress-circular>
      </v-col>
    </v-col>
  </div>
</template>

<script>
import ex from '../../../../public/ex';

export default {
  name: 'CalendarDetail',
  props: {
    record: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      ex,
    };
  },
  methods: {
    calSuccess(count, fail) {
      return Math.floor((count / (count + fail)) * 100);
    },
    convertTime(seconds) {
      const hour = parseInt(seconds / 3600, 10);
      const min = parseInt((seconds % 3600) / 60, 10);
      const sec = seconds % 60;
      return hour > 0 ? `${hour}:${min}:${sec}` : `${min}:${sec}`;
    },
  },
};
</script>

<style scoped>
.timeBox {
  margin-left: 10px;
  height: 77px;
  /* border: 1px solid; */
  background: #ffd2b625;
  border-radius: 30px 0 0 30px;
  padding: 0;
}

.exBox {
  height: 77px;
  /* border: 1px solid; */
  background: #FF8F4A25;
  border-radius: 0 30px 30px 0;
  padding: 0;
}
</style>
