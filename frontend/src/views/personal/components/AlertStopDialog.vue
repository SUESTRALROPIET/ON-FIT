<template>
  <v-dialog
    v-model="showDialog"
    width="30rem"
    persistent
  >
    <v-card id="alert-dialog">
      <v-card-text class="mt-10">
        <h3>운동을 이어서 하시겠습니까?</h3>
      </v-card-text>
      <v-card-actions class="d-flex justify-center">
        <v-btn icon @click="$emit('restart')">
          <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlterStopDialog',
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
