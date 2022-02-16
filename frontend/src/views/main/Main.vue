// TODO: userID 변경
<template>
  <div id="main-box" class="d-flex flex-column align-center">
    <div id="trainer-lst" class="mb-5">
      <v-row>
        <v-col
          v-for="n in 3"
          :key="n"
          class="d-flex child-flex"
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
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Main',
  data() {
    return {
      trainerNum: '',
    };
  },
  created() {
    this.getTrainer();
    this.$store.dispatch('getExDays');
    this.$store.dispatch('getTime');
    this.$store.dispatch('getCal');
  },
  methods: {
    selectTrainer(num) {
      // TODO: PATCH Test 필요
      const userId = 1;
      this.trainerNum = num;
      axios.patch(`http://localhost:8081/personal/${userId}`, { trainer_id: this.trainerNum }, {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-type': 'application/json',
        },
      })
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
      const userId = 1;
      axios.get(`http://localhost:8081/personal/${userId}`)
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
