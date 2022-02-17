<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager"/>
    <div>
      <p>{{ clientData }}</p>
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
