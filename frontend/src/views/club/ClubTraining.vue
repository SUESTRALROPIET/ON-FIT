<template>
  <div>
    {{ClubInfo}}
    <div>
      <ClubTrainingExRecord
        :ClubInfo="ClubInfo"
      />
    </div>
    <div>
      <ClubTrainingUserList/>
    </div>
  </div>
</template>

<script>
import ClubTrainingExRecord from '@/views/club/components/ClubTrainingExRecord.vue';
import ClubTrainingUserList from '@/views/club/components/ClubTrainingUserList.vue';

export default {
  name: 'ClubTraining',
  components: {
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
