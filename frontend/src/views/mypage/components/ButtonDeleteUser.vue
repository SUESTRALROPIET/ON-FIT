<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      max-width="30rem"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          width="100%"
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          회원탈퇴
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
        </v-card-title>
        <v-card-text class="my-4">
          <div class="d-flex justify-center">
            <h3>정말 탈퇴하시겠습니까?</h3>
          </div>
        </v-card-text>
        <v-card-actions class="d-flex justify-center">
          <v-btn icon>
            <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-close-circle-outline</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: 'ButtonDeleteUser',
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style>

</style>
