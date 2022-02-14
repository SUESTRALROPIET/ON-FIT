<template>
  <div>
    <div class="mb-15">
      <h2 class="my-5">가입한 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="UserClub in UserClubs"
          :key="UserClub.club_name"
          :ClubInfo="UserClub"
        />
      </div>
    </div>
    <div class="mb-15">
      <h2 class="my-5">지금 진행중인 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="ReadyClub in ReadyClubs"
          :key="ReadyClub.club_name"
          :ClubInfo="ReadyClub"
        />
      </div>
    </div>
    <div class="mb-15">
      <h2 class="my-5">신규 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="NewClub in NewClubs"
          :key="NewClub.club_name"
          :ClubInfo="NewClub"
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
      UserClubs: [{
        club_id: 1,
        club_name: '클럽21',
        club_img: require('@/assets/club/club_default.png'),
        Participation_day: {
          mon: false,
          tues: false,
          wedn: false,
          thur: false,
          fri: false,
          sau: false,
          sun: true,
        },
        manager: '클럽장이름',
        start_date: '2019-09-10',
        end_date: '2019-09-20',
        fix_time: '15:00',
        count: 6,
        finish: false,
        club_mate: {
          user_id: '클럽원1',
        },
        club_log: [
          {
            id: 1,
            exercise_id: 1,
            exercise_count: 1,
            exercise_time: '운동시간',
          },
          {
            id: 2,
            exercise_id: 1,
            exercise_count: 2,
            exercise_time: '운동시간',
          },
        ],
      }],
      ReadyClubs: [{
        club_name: '클럽2',
        club_img: require('@/assets/club/club_default.png'),
        Participation_day: {
          mon: true,
          tues: false,
          wedn: false,
          thur: false,
          fri: false,
          sau: false,
          sun: false,
        },
        manager: '클럽장이름',
        start_date: '2019-09-10',
        end_date: '2019-09-20',
        fix_time: '15:00',
        count: 6,
        finish: false,
      }],
      NewClubs: [{
        club_name: '클럽3',
        club_img: require('@/assets/club/club_default.png'),
        Participation_day: {
          mon: false,
          tues: false,
          wedn: true,
          thur: false,
          fri: false,
          sau: false,
          sun: false,
        },
        manager: '클럽장이름',
        start_date: '2019-09-10',
        end_date: '2019-09-20',
        fix_time: '15:00',
        count: 6,
        finish: false,
      }],
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
