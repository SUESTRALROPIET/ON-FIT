<template>
  <div
    id="mouserover-club"
    @click="enterClub"
  >
    <img :src='`${ ClubInfo.club_img }`' alt="club-image">
    <h3 class="my-3">{{ ClubInfo.club_name }}</h3>
    <div class="d-flex justify-space-between">
      <div class="d-flex">
        <span><v-icon>mdi-alarm</v-icon></span>
        <p>
          <span v-if="ClubInfo.Participation_day['mon']">월</span>
          <span v-if="ClubInfo.Participation_day['tues']">화</span>
          <span v-if="ClubInfo.Participation_day['wedn']">수</span>
          <span v-if="ClubInfo.Participation_day['thur']">목</span>
          <span v-if="ClubInfo.Participation_day['fri']">금</span>
          <span v-if="ClubInfo.Participation_day['sau']">토</span>
          <span v-if="ClubInfo.Participation_day['sun']">일</span>
          {{ ClubInfo.fix_time }}
        </p>
      </div>
      <div class="d-flex">
        <span><v-icon>mdi-account-circle</v-icon></span>
        <p>{{ ClubInfo.count }} / 6</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ClubListElement',
  props: {
    ClubInfo: Object,
  },
  data() {
    return {
    };
  },
  methods: {
    enterClub() {
      this.$router.push({
        name: 'ClubTraining',
        query: { ClubInfo: this.ClubInfo },
      });
    },
  },
};
</script>

<style>
  #mouserover-club {
    cursor: pointer;
  }
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
