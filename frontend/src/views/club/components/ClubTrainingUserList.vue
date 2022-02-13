<template>
  <div>
    유저리스트
    <ClubTrainingUser/>
  </div>
</template>

<script>
import ClubTrainingUser from '@/views/club/components/ClubTrainingUser.vue';

export default {
  name: 'ClubTrainingUserList',
  components: {
    ClubTrainingUser,
  },
};
</script>

<style>

</style>
