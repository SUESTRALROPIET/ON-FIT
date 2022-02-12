<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          width="100%"
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          내 클럽 보기
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">내 클럽보기</span>
        </v-card-title>
        <v-card-text>
          <h2>생성한 클럽</h2>
          <h2>가입한 클럽</h2>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: 'ButtonClubs',
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style>

</style>
