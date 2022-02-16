<template>
  <div>
    <div
      class="d-flex flex-column align-end"
    >
      <div
        id="club-main-btn"
        class="pb-5"
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
    };
  },
  methods: {
  },
  created() {
    this.$store.dispatch('getClubList');
  },
  computed: {
    ClubList() {
      return this.$store.state.clubStore.storeClubList;
    },
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
