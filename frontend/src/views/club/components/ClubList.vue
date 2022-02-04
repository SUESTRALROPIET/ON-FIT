<template>
  <div>
    <div class="mb-15">
      <h2 class="my-5">가입한 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="index in 2"
          :key="index"
        />
      </div>
    </div>
    <div class="mb-15">
      <h2 class="my-5">지금 진행중인 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="index in 10"
          :key="index"
        />
      </div>
    </div>
    <div class="mb-15">
      <h2 class="my-5">신규 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="index in 5"
          :key="index"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ClubListElement from '@/views/club/components/ClubListElement.vue';

export default {
  name: 'ClubList',
  components: {
    ClubListElement,
  },
  data() {
    return {
      UserClub: {
        club_name: '클럽1',
        club_img: require('@/assets/club/club_default.png'),
        Participation_day: {
          mon: true,
          tues: true,
          wedn: true,
          thur: true,
          fri: true,
          sau: true,
          sun: true,
        },
        manager: '클럽장이름',
        start_date: '2019-09-10',
        end_date: '2019-09-20',
        fix_time: '15:00',
        count: 6,
        finish: false,
      },
    };
  },
};
</script>

<style scoped>
.gallery-item {
  display: inline-block;
  margin: 0px 36px 0px 0px;
}
</style>
