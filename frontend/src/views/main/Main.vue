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
export default {
  name: 'Main',
  data() {
    return {
      trainerNum: 1,
    };
  },
  methods: {
    selectTrainer(num) {
      this.trainerNum = num;
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
