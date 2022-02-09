<template>
  <v-dialog
    width="70rem"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        width="40%"
        rounded
        outlined
        v-bind="attrs"
        v-on="on"
        style="background-color: white;"
      >
        <span>클럽관리</span>
      </v-btn>
    </template>
    <DialogManageClub
      :ClubInfo="ClubInfo"
    />
  </v-dialog>
</template>
<script>
import DialogManageClub from '@/views/club/components/DialogManageClub.vue';

export default {
  name: 'ManageClubButton',
  components: {
    DialogManageClub,
  },
  props: {
    ClubInfo: Object,
  },
};
</script>
<style scoped>
#manage-club-form {
  padding: 2rem;
  border-radius: 1rem;
}
#manage-club-button {
  color: black;
  background-color: white;
}
</style>
