<template>
  <div
    style="width: 40%"
    @click="enterClub"
  >
    <img
      src='@/assets/club/club_default.png' alt="club-image"
      width="100%" style="border-radius: 1rem;"
    >
    <!-- <img :src='`${ SearchResult.clubInfo.clubImg }`'> -->
    <h3 class="my-3">{{ SearchResult.clubInfo.clubName }}</h3>
    <div class="d-flex justify-space-between">
      <div class="d-flex">
        <span><v-icon>mdi-alarm</v-icon></span>
        <p>
          <span v-if="SearchResult.clubInfo.mon">월</span>
          <span v-if="SearchResult.clubInfo.tues">화</span>
          <span v-if="SearchResult.clubInfo.wedn">수</span>
          <span v-if="SearchResult.clubInfo.thur">목</span>
          <span v-if="SearchResult.clubInfo.fri">금</span>
          <span v-if="SearchResult.clubInfo.sat">토</span>
          <span v-if="SearchResult.clubInfo.sun">일</span>
          <span>{{new Date(SearchResult.clubInfo.fixTime).getHours()}}</span>
          <span>:</span>
          <span>{{new Date(SearchResult.clubInfo.fixTime).getMinutes()}}</span>
        </p>
      </div>
      <div class="d-flex">
        <span><v-icon>mdi-account-circle</v-icon></span>
        <p>{{ SearchResult.clubMate.length }} / 6</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchBarResults.',
  props: {
    SearchResult: Object,
  },
  data() {
    return {
    };
  },
  methods: {
    enterClub() {
      this.$router.push({
        name: 'ClubTraining',
        query: { ClubInfo: this.SearchResult },
      });
    },
  },
};
</script>

<style>

</style>
