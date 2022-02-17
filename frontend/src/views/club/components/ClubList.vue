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
import Vue from 'vue';
import Vuex, { mapGetters } from 'vuex';
import _ from 'lodash';
import ClubListElement from '@/views/club/components/ClubListElement.vue';

Vue.use(Vuex);
const userStore = 'userStore';

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
    };
  },
  methods: {
    ...mapGetters(userStore, [
      'getUserId',
    ]),
    getUserClubList() {
      return _.orderBy(this.ClubList.filter((club) => club.clubMate.includes(this.getUserId())), ['clubInfo.createdAt'], ['desc']);
    },
    /* eslint-disable */
    getReadyClubList() {
      const NotJoined = this.ClubList.filter(x => !this.getUserClubList().includes(x));
      // const dateList = ['sun', 'mon', 'tues', 'wedn', 'thur', 'fri', 'sat'];
      const now = new Date();
      const nowDate = now.getDay();
      let readyClubs = [];
      if (nowDate === 0) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.sun === true);
      } else if (nowDate === 1) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.mon === true);
      } else if (nowDate === 2) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.tues === true);
      } else if (nowDate === 3) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.wedn === true);
      } else if (nowDate === 4) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.thur === true);
      } else if (nowDate === 5) {
        readyClubs = NotJoined.filter((club) => club.clubInfo.fri === true);
      } else {
        readyClubs = NotJoined.filter((club) => club.clubInfo.sat === true);
      }
      return  _.orderBy(readyClubs, ['clubInfo.createdAt'], ['desc']);
    },
    getNewClubList() {
      const NotJoined = this.ClubList.filter(x => !this.getUserClubList().includes(x));
      const now = new Date();
      const newClubs = NotJoined.filter((club) => {
        const clubDate = new Date(club.clubInfo.createdAt);
        const diffTime = Math.floor((now.getTime() - clubDate.getTime()) / 1000 / 60);
        const diffDate = Math.floor(diffTime / 60 / 24);
        return diffDate <= 7; // 현재기준, 1주 내에 생성된 클럽만!
      });
      return _.orderBy(newClubs, ['clubInfo.createdAt'], ['desc']);
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
