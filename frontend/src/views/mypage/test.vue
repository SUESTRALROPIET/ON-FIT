<template>
  <div>
    <p>test</p>
    <!-- <Calendar /> -->
    <rangeCal />
  </div>
</template>

<script>
// import Calendar from './components/Calendar.vue';
import rangeCal from './components/rangeCal.vue';

export default {
  name: 'test',

  components: {
    // Calendar,
    rangeCal,
  },
  data() {
    return {
      chartData: {
        Books: 24,
        Magazine: 30,
        Newspapers: 10,
      },
    };
  },
};
</script>

<style>

</style>
