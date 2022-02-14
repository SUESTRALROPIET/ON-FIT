<template>
  <div
    id="mouserover-club"
    @click="enterClub"
    class="d-flex flex-column"
  >
    <div>
      <div class="text d-flex align-center justify-center">
        <img
          src='@/assets/club/club_default.png' alt="club-image"
          class="align-self-center"
          :class="(ClubInfo.clubInfo.finish)? 'finish-club' : ''"
        >
        <!-- <img :src='`${ ClubInfo.clubInfo.clubImg }`' alt="club-image"-->
        <h2
          v-if="ClubInfo.clubInfo.finish"
          class="flex-child"
        >
          모집 완료
        </h2>
      </div>
    </div>
    <h3 class="my-3">{{ ClubInfo.clubInfo.clubName }}</h3>
    <div class="d-flex justify-space-between">
      <div class="d-flex">
        <span><v-icon>mdi-alarm</v-icon></span>
        <p>
          <span v-if="ClubInfo.clubInfo.mon">월</span>
          <span v-if="ClubInfo.clubInfo.tues">화</span>
          <span v-if="ClubInfo.clubInfo.wedn">수</span>
          <span v-if="ClubInfo.clubInfo.thur">목</span>
          <span v-if="ClubInfo.clubInfo.fri">금</span>
          <span v-if="ClubInfo.clubInfo.sat">토</span>
          <span v-if="ClubInfo.clubInfo.sun">일</span>
          <span>{{new Date(ClubInfo.clubInfo.fixTime).getHours()}}</span>
          <span>:</span>
          <span>{{new Date(ClubInfo.clubInfo.fixTime).getMinutes()}}</span>
        </p>
      </div>
      <div class="d-flex">
        <span><v-icon>mdi-account-circle</v-icon></span>
        <p>{{ ClubInfo.clubMate.length }} / 6</p>
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
.finish-club {
  filter: invert(0.3);
}
.gallery-item img {
  position: relative;
  height: 200px;
  width: 200px;
  border-radius: 1rem;
}
.text h2 {
  position: absolute;
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
