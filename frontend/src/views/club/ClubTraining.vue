<template>
  <div>
    {{ClubInfo}}
    <div
      class="d-flex"
    >
      <ClubTrainingExList
        style="width: 70%;"
      />
      <ClubTrainingExRecord
        style="width: 30%;"
        :ClubInfo="ClubInfo"
      />
    </div>
    <ClubTrainingUserList/>
  </div>
</template>

<script>
import ClubTrainingExList from '@/views/club/components/ClubTrainingExList.vue';
import ClubTrainingExRecord from '@/views/club/components/ClubTrainingExRecord.vue';
import ClubTrainingUserList from '@/views/club/components/ClubTrainingUserList.vue';

export default {
  name: 'ClubTraining',
  components: {
    ClubTrainingExList,
    ClubTrainingExRecord,
    ClubTrainingUserList,
  },
  data() {
    return {
      ClubInfo: {},
    };
  },
  created() {
    this.ClubInfo = this.$route.query.ClubInfo;
  },
};
</script>

<style>

</style>
