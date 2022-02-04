<template>
  <div>
    <img :src='`${ UserClub.club_img }`' alt="club-image">
    <h3 class="my-3">{{ UserClub.club_name }}</h3>
    <div class="d-flex justify-space-between">
      <div class="d-flex">
        <span><v-icon>mdi-alarm</v-icon></span>
        <p>{{ UserClub.Participation_day.mon }} {{ UserClub.fix_time }}</p>
      </div>
      <div class="d-flex">
        <span><v-icon>mdi-account-circle</v-icon></span>
        <p>{{ UserClub.count }} / {{ UserClub.count }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ClubListElement',
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

<style>
.gallery-item > img {
  height: 200px;
  width: 200px;
  border-radius: 1rem;
}
.gallery-item span {
  margin: 0px 5px 0px 0px;
}
.gallery-container {
  display: flex;
  flex-direction: row;
  overflow-x: scroll;
}
</style>
