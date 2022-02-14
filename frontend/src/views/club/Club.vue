<template>
  <div>
    <div
      class="d-flex flex-column align-end"
    >
      <div
        id="club-main-btn"
      >
        <v-btn
          small
          rounded
          plain
          elevation="1"
          @click="OpenSearchClubDrawer = true"
        >
          <v-icon>mdi-magnify</v-icon>
          <span>클럽찾기</span>
        </v-btn>
        <ButtonCreateClub />
      </div>
      <v-checkbox
        v-model="clubFinish"
        label="모집중인 클럽만 보기"
        color="success"
        @click="clickFinish"
      ></v-checkbox>
    </div>
    <ClubList
      :ClubList="ClubList"
    />
    <v-navigation-drawer
      id="search-drawer"
      v-model="OpenSearchClubDrawer"
      fixed
      temporary
      right
      overlay-color="transparent"
      width="28rem"
      height="100%"
    >
      <div>
        <SearchBar
          :ClubList="ClubList"
        />
      </div>
    </v-navigation-drawer>
  </div>
</template>

<script>
import axios from 'axios';

import ClubList from '@/views/club/components/ClubList.vue';
import SearchBar from '@/views/club/components/SearchBar.vue';
import ButtonCreateClub from '@/views/club/components/ButtonCreateClub.vue';

export default {
  name: 'Club',
  components: {
    ClubList,
    SearchBar,
    ButtonCreateClub,
  },
  data() {
    return {
      OpenSearchClubDrawer: false,
      clubFinish: false,
      ClubList: [
        {
          clubInfo: {
            id: 5,
            clubName: '클럽1',
            clubImg: 'string',
            mon: true,
            tues: false,
            wedn: false,
            thur: true,
            fri: true,
            sat: false,
            sun: false,
            manager: '클럽장1',
            createdAt: '2022-02-12T16:35:17.819',
            startDate: '2022-02-12T16:35:17.819',
            endDate: '2022-02-12T16:35:17.819',
            fixTime: '2022-02-12T16:35:17.819',
            count: 0,
            finish: false,
          },
          clubMate: ['유저1', '유저2', '현재로그인한사용자'],
          clubLog: [],
        },
        {
          clubInfo: {
            id: 6,
            clubName: '클럽2',
            clubImg: 'string',
            mon: false,
            tues: false,
            wedn: false,
            thur: false,
            fri: true,
            sat: true,
            sun: true,
            manager: '클럽장2',
            createdAt: '2022-02-02T16:35:17.819',
            startDate: '2022-02-12T16:35:17.819',
            endDate: '2022-02-12T16:35:17.819',
            fixTime: '2022-02-12T16:35:17.819',
            count: 0,
            finish: true,
          },
          clubMate: ['유저3'],
          clubLog: [],
        },
      ],
    };
  },
  methods: {
    getClubList() {
      axios.get('http://localhost:8081/club/')
        .then((response) => {
          this.ClubList = response.data;
        });
    },
    clickFinish() {
      // this.getClubList();
      if (this.clubFinish) {
        this.ClubList = this.ClubList.filter((club) => club.clubInfo.finish !== this.clubFinish);
      } else {
        // this.getClubList();
      }
    },
  },
  mounted() {
    // this.getClubList();
  },
};
</script>

<style scoped>
#club-main-btn .v-btn{
  height: 2rem;
  width: 9rem;
  margin: 0rem 0rem 0rem 1rem;
}
#club-main-btn span{
  font-size: 1.2rem;
}
#search-drawer {
  background-color: transparent;
  box-shadow: 0px 0px 0px;
}
</style>
