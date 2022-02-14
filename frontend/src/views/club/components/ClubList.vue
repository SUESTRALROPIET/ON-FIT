<template>
  <div>
    <div class="mb-15">
      <h2 class="my-5">가입한 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="UserClub in getUserClubList()"
          :key="UserClub.club_name"
          :ClubInfo="UserClub"
        />
      </div>
    </div>
    <div class="mb-15">
      <h2 class="my-5">오늘의 클럽</h2>
      <div class="gallery-container">
        <ClubListElement
          class="gallery-item"
          v-for="ReadyClub in getReadyClubList()"
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
          v-for="NewClub in getNewClubList()"
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
  props: {
    ClubList: Array,
  },
  data() {
    return {
      LogginedUser: '현재로그인한사용자',
    };
  },
  methods: {
    getUserClubList() {
      return this.ClubList.filter((club) => club.clubMate.includes(this.LogginedUser));
    },
    /* eslint-disable */
    getReadyClubList() {
      // const dateList = ['sun', 'mon', 'tues', 'wedn', 'thur', 'fri', 'sat'];
      const now = new Date();
      const nowDate = now.getDay();
      let readyClubs = [];
      if (nowDate === 0) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.sun === true);
      } else if (nowDate === 1) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.mon === true);
      } else if (nowDate === 2) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.tues === true);
      } else if (nowDate === 3) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.wedn === true);
      } else if (nowDate === 4) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.thur === true);
      } else if (nowDate === 5) {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.fri === true);
      } else {
        readyClubs = this.ClubList.filter((club) => club.clubInfo.sat === true);
      }
      return readyClubs;
    },
    getNewClubList() {
      const now = new Date();
      const newClubs = this.ClubList.filter((club) => {
        const clubDate = new Date(club.clubInfo.createdAt);
        const diffTime = Math.floor((now.getTime() - clubDate.getTime()) / 1000 / 60);
        const diffDate = Math.floor(diffTime / 60 / 24);
        return diffDate <= 7; // 현재기준, 1주 내에 생성된 클럽만!
      });
      return newClubs;
    },
  },
};
</script>

<style scoped>
.gallery-item {
  display: inline-block;
  margin: 0px 36px 0px 0px;
}
</style>
