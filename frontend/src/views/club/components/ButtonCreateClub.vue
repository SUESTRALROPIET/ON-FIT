<template>
  <v-dialog
    width="70rem"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        small
        rounded
        color="amber lighten-5"
        elevation="1"
        v-bind="attrs"
        v-on="on"
        @click="dialog = false"
      >
        <span>클럽 만들기</span>
      </v-btn>
    </template>
    <FormCreateClub/>
  </v-dialog>
</template>
<script>
import FormCreateClub from '@/views/club/components/FormCreateClub.vue';

export default {
  name: 'ButtonCreateClub',
  components: {
    FormCreateClub,
  },
  data() {
    return {
    };
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
