<template>
  <v-dialog
    width="45rem"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        small
        rounded
        color="amber lighten-5"
        elevation="1"
        v-bind="attrs"
        v-on="on"
      >
        <span>클럽 만들기</span>
      </v-btn>
    </template>
    <DialogCreateClub/>
  </v-dialog>
</template>
<script>
import DialogCreateClub from '@/views/club/components/DialogCreateClub.vue';

export default {
  name: 'CreateClubButton',
  components: {
    DialogCreateClub,
  },
};
</script>
<style scoped>
#club-main-btn .v-btn{
  height: 2rem;
  width: 9rem;
  margin: 0rem 0rem 0rem 1rem;
}
#club-main-btn span{
  font-size: 1.2rem;
}
</style>
