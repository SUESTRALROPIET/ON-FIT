<template>
  <div id="main-box" class="d-flex flex-column align-center">
    <div id="trainer-lst" class="mb-10">
      <v-row>
        <v-col
          v-for="n in 3"
          :key="n"
          class="d-flex flex-column child-flex"
          cols="4"
          @click="selectTrainer(n)"
        >
          <v-img
            :class="(n === trainerNum)? 'show-selected-trainer' : ''"
            class="hover-trainer"
            id="trainer-img"
            :src="require(`@/assets/trainer/trainer_${n}.png`)"
            alt="trainer-image"
          >
          </v-img>
          <div class="align-self-center pt-5">
            <h4 v-if="n === 1">#Naomi #스파르타</h4>
            <h4 v-if="n === 2">#Ryan #친절한 라이언씨</h4>
            <h4 v-if="n === 3">#Summer #목표만 집중!</h4>
          </div>
        </v-col>
      </v-row>
    </div>
    <div class="d-flex">
      <v-row class="justify-center">
        <v-col cols="4" class="d-flex child-flex"
          @click="enterPersonalTraining"
        >
          <img
            id="show-selected-course"
            src="@/assets/main/main_personal.jpg"
            alt="personal-training-image"
          />
        </v-col>
        <v-col cols="4" class="d-flex child-flex"
          @click="enterClubTraining"
        >
          <img
            id="show-selected-course"
            src="@/assets/main/main_club.jpg"
            alt="club-training-image"
          />
        </v-col>
      </v-row>
    </div>
    <ButtonLogin style="visibility: hidden;"/>
  </div>
</template>

<script>
import Vue from 'vue';
import Vuex, { mapGetters } from 'vuex';
import ButtonLogin from '@/views/intro/components/ButtonLogin.vue';

import { apiInstance } from '@/api/index';

Vue.use(Vuex);
const userStore = 'userStore';

export default {
  name: 'Main',
  components: {
    ButtonLogin,
  },
  data() {
    return {
      trainerNum: '',
    };
  },
  created() {
    this.getTrainer();
    const userId = this.getUserId();
    this.$store.dispatch('getExDays', userId);
    this.$store.dispatch('getTime', userId);
    this.$store.dispatch('getCal', userId);
  },
  methods: {
    ...mapGetters(userStore, [
      'getUserId',
    ]),
    selectTrainer(num) {
      const api = apiInstance();
      const userId = this.getUserId();
      this.trainerNum = num;
      api.patch(`/personal/${userId}`, this.trainerNum)
        .then((res) => console.log(res))
        .catch((err) => console.err(err));
    },
    enterPersonalTraining() {
      this.$router.push({
        name: 'Personal',
      });
    },
    enterClubTraining() {
      this.$router.push({
        name: 'Club',
      });
    },
    getTrainer() {
      const api = apiInstance();
      const userId = this.getUserId();
      api.get(`/personal/${userId}`)
        .then((res) => {
          this.trainerNum = res.data.id;
        });
    },
  },
};
</script>

<style scoped>
#main-box img {
  box-shadow: rgba(0, 0, 0, 0.25) 0px 3px 8px;
  cursor: pointer;
}
#trainer-img {
  box-shadow: rgba(0, 0, 0, 0.25) 0px 3px 8px;
  cursor: pointer;
}
.hover-trainer:hover {
  filter: grayscale(80%);
}
#trainer-lst {
  width: 50%;
}
.show-selected-trainer {
  border: 3px black solid;
}
#show-selected-course:hover {
  transform: translateY(0) scale(1.2);
}
</style>
