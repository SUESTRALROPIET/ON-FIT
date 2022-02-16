<template>
  <v-dialog
    v-model="showDialog"
    width="30rem"

  >
    <v-card id="alert-dialog">
      <!-- <v-progress-linear
        :active="loading"
        :indeterminate="loading"
        absolute
        height="7px"
        top
        color="orange lighten-2"
        rounded
      ></v-progress-linear> -->
      <HollowDotsSpinner
        class="mx-auto"
        :animation-duration="1100"
        :dot-size="15"
        :dots-num="3"
        color="#ff1d5e"
      />
      <v-card-text class="mt-10">
        <h3 class="mb-3">화면을 준비중입니다.</h3>
        <h3>카메라와 소리를 켜고, 잠시만 기다려 주세요.</h3>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import { HollowDotsSpinner } from 'epic-spinners';

export default {
  name: 'AlertLoadingDialog',
  components: {
    HollowDotsSpinner,
  },
  props: {
    showDialog: Boolean,
  },
  data() {
    return {
      // loading: true,
    };
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
