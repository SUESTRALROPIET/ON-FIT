<template>
  <div id="app">
    <div class="container">
      <div id="scene">
        <h2 id="text">
          <span>o</span>
          <span>n</span>
          <span>:</span>
          <span>F</span>
          <span>I</span>
          <span>T</span>
        </h2>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Intro',
  components: {
  },
};
</script>

<style scoped>
#app {
  position: fixed;
}
.container {
  position: absolute;
  width: 100vw;
  height: 100vh;
  background: radial-gradient(#9cdcff, #1cafff)
}
.container #scene{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.container #scene #text {
  position: absolute;
  display: flex;
  position: relative;
  color: #fff;
  font-size: 14vw;
  z-index: 100;
}
.container #scene #text span {
  position: relative;
  display: inline-block;
  text-shadow: 1px 1px 0 #e1d1d1,
  2px 2px 0 #e1d1d1,
  3px 3px 0 #e1d1d1,
  4px 4px 0 #e1d1d1,
  5px 5px 0 #e1d1d1,
  6px 6px 0 #e1d1d1,
  7px 7px 0 #e1d1d1,
  8px 8px 0 #e1d1d1,
  25px 35px 0 rgba(0, 0, 0, 0.1);
}
</style>
