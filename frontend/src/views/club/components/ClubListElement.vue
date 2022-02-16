<template>
  <div>
    <div class="text-center">
      <v-dialog
        v-model="showFormJoinClub"
        width="50rem"
      >
        <FormJoinClub
        :ClubInfo="ClubInfo"
        />
      </v-dialog>
    </div>
    <div class="text-center">
      <v-dialog
        v-model="showFailJoinClub"
        width="30rem"
      >
        <AlertFailJoinClub/>
      </v-dialog>
    </div>
    <div
      id="mouserover-club"
      @click="enterClub(getUserId())"
      class="d-flex flex-column"
    >
      <div>
        <div class="text d-flex align-center justify-center">
          <img
            src='@/assets/club/club_default.png' alt="club-image"
            class="align-self-center"
            :class="(ClubInfo.clubMate.length >= 6)? 'finish-club' : ''"
          >
          <!-- <img :src='`${ ClubInfo.clubInfo.clubImg }`' alt="club-image"-->
          <h2
            v-if="ClubInfo.clubMate.length >= 6"
            class="flex-child"
          >
            모집 완료
          </h2>
        </div>
      </div>
      <div class="d-flex">
        <span><v-icon>mdi-account-circle</v-icon></span>
        <p>{{ ClubInfo.count }} / 6</p>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import Vuex, { mapGetters } from 'vuex';

Vue.use(Vuex);
const userStore = 'userStore';

export default {
  name: 'ClubListElement',
  props: {
    ClubInfo: Object,
  },
  data() {
    return {
      showFormJoinClub: false,
      showFailJoinClub: false,
    };
  },
  methods: {
    ...mapGetters(userStore, [
      'getUserId',
    ]),
    enterClub(userId) {
      if (this.ClubInfo.clubMate.includes(userId)) {
        this.$router.push({
          name: 'ClubTraining',
          query: { ClubInfo: this.ClubInfo },
        });
      } else if (this.ClubInfo.clubMate.length === 6) {
        this.showFailJoinClub = true;
      } else {
        this.showFormJoinClub = true;
      }
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
