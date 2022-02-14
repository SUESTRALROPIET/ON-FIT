<template>
  <v-dialog
    v-model="showDialog"
    width="30rem"
  >
    <v-card id="alert-dialog">
      <v-card-text class="mt-10">
        <h3>선택된 운동이 없습니다.</h3>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlertNotSelect',
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
