<template>
  <v-dialog
    v-model="showDialog"
    width="20rem"
    persistent
  >
    <v-card id="alert-break-time" class="d-flex flex-column align-center">
      <v-card-title>
        <h2>휴식</h2>
      </v-card-title>
      <v-card-text class="mt-10">
        <v-progress-circular
          :rotate="360"
          :size="150"
          :width="30"
          :value="value"
          color="rgb(64, 64, 64)"
        >
          <span>{{ timer }}</span>
        </v-progress-circular>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlertBreakTime',
  props: {
    showDialog: Boolean,
  },
  data() {
    return {
      interval: {},
      value: 100,
      timer: 60,
    };
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  mounted() {
    this.interval = setInterval(() => {
      if (this.showDialog) {
        if (this.value < 0) {
          this.value = 100;
          this.timer = 60;
        }
        this.value -= 1.6666666666666667;
        this.timer -= 1;
      }
    }, 1000);
  },
};
</script>

<style scoped>
#alert-break-time {
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
}
#alert-break-time span{
  font-size: 30px;
}
</style>
