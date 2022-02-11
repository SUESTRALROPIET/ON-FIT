<template>
  <v-dialog
    v-model="showDialog"
    width="25rem"
  >
    <v-card id="alert-dialog">
      <v-card-text class="mt-10">
        <h2 class="mb-3">운동 완료!</h2>
        <h3>운동 기록을 확인하시겠습니까?</h3>
      </v-card-text>
      <v-card-actions class="d-flex justify-center">
        <v-btn icon @click="$emit('exit2')">
          <v-icon >mdi-checkbox-marked-circle-outline</v-icon>
        </v-btn>
        <v-btn icon @click="$emit('exit3')">
          <v-icon>mdi-close-circle-outline</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlertFinishDialog',
  props: {
    showDialog: Boolean,
  },
};
</script>

<style scoped>
#alert-dialog {
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
}
</style>
