<template>
  <v-dialog
    v-model="showDialog"
    width="30rem"
  >
    <v-card id="alert-fail-join-club">
      <v-card-text class="mt-10">
        <p>정원 초과로 클럽 가입이 불가합니다</p>
      </v-card-text>
      <v-card-actions class="d-flex justify-center">
        <v-btn
          icon
        >
          <v-icon>mdi-checkbox-marked-circle</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlertFailJoinClub',
  props: {
    showDialog: Boolean,
  },
};
</script>

<style>
#alert-fail-join-club {
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
}
</style>
