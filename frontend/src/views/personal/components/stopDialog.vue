<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
    >
      <v-card>
        <v-card-title class="text-h5">
          운동을 이어서 하시겠습니까?
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            icon
            color="black"
            @click="dialog = false"
          >
            <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: 'stopDialog',

  data() {
    return {
      dialog: false,
    };
  },
};
</script>
