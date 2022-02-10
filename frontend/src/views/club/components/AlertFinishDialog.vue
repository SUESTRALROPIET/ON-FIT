<template>
  <v-dialog
    v-model="showDialog"
    width="25rem"
    persistent
  >
    <v-card id="alert-dialog">
      <v-card-text class="mt-10">
        <h2>운동 완료!</h2>
      </v-card-text>
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

<style>
#alert-dialog {
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
}
</style>
