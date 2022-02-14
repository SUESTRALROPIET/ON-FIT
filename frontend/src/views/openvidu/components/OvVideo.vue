<template>
  <!-- <div> -->
    <video autoplay />
  <!-- </div> -->
</template>

<script>
export default {
  name: 'OvVideo',

  props: {
    streamManager: Object,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
