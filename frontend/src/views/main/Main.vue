<template>
  <div class="d-flex flex-column align-center">
    <div style="width:50%;">
      <v-row>
        <v-col
          v-for="n in 3"
          :key="n"
          class="d-flex child-flex"
          cols="4"
        >
          <v-img
            :src="require(`@/assets/trainer/trainer_${n}.png`)"
            aspect-ratio="1"
          >
          </v-img>
        </v-col>
      </v-row>
    </div>
    <div class="d-flex">
      <div>

      </div>
      <!-- <v-row>
        <v-col
          class="d-flex"
          cols="6"
        >
          <img
            src="@/assets/main/main_personal.jpg"
            aspect-ratio="1"
          />
        </v-col>
        <v-col
          class="d-flex"
          cols="6"
        >
          <img
            src="@/assets/main/main_club.jpg"
            aspect-ratio="1"
          />
        </v-col>
      </v-row> -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',

  components: {
  },
};
</script>

<style>

</style>
