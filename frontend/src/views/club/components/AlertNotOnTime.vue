<template>
  <v-dialog
    v-model="show"
    width="30rem"
  >
    <v-card id="alert-fail-join-club">
      <v-card-text class="mt-10">
        <p>운동 시간이 아닙니다.</p>
      </v-card-text>
      <v-card-actions class="d-flex justify-center">
        <v-btn
          icon
        >
          <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'AlertFailJoinClub',
  props: {
    value: Boolean,
  },
  computed: {
    show: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit('input', value);
      },
    },
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
