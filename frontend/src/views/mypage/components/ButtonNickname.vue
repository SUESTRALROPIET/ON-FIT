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
          닉네임 수정
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">닉네임 수정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <div class="d-flex align-center mx-5">
              <v-col cols="4">
                <h3>ID</h3>
              </v-col>
              <v-col>
                <h3>{{user_info.user_email}}</h3>
              </v-col>
            </div>
            <div class="d-flex align-center mx-5">
              <v-col cols="4">
                <h3>Nickname</h3>
              </v-col>
              <v-col>
                <v-text-field
                  v-model="user_info.full_name"
                  label="닉네임 수정"
                  required
                ></v-text-field>
              </v-col>
            </div>
          </v-container>
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
  name: 'ButtonNickname',
  props: {
    user_info: Object,
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style>

</style>
