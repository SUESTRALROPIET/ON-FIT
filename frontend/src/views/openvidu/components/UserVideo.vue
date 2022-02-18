<template>
  <div v-if="streamManager" class="d-flex flex-column align-center">
    <ov-video :stream-manager="streamManager"
      style="border: solid 1px rgba(0, 0, 0, 0.5);
      border-top-right-radius: 1rem;
      border-top-left-radius:  1rem;
      max-width: 100%"
    />
    <div
      class="py-1"
      style="width: 100%; text-align: center;
      background-color: rgba(255, 255, 255, 0.5);
      border: solid 1px rgba(0, 0, 0, 0.5);
      border-bottom-right-radius: 1rem;
      border-bottom-left-radius:  1rem;"
    >
      <h3>{{ clientData }} 님</h3>
    </div>
  </div>
</template>

<script>
import OvVideo from './OvVideo.vue';

export default {
  name: 'UserVideo',

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object, // 이게 App.vue의 subscriber
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      console.log(`하이루${clientData}`);
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
<style scoped>
</style>
