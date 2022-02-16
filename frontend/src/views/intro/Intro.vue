<template>
  <div id="main" class="d-flex flex-column align-center">
    <div
      class="text"
    >
      on:FIT
    </div>
    <!-- <div class="btn-enter-service">
      <div class="d-flex justify-center align-center">
        <span>on:FIT하러 가기</span>
      </div>
    </div> -->
    <div id="loginBox">
      <ButtonLogin />
    </div>
  </div>
</template>

<script>
import ButtonLogin from './components/ButtonLogin.vue';

export default {
  name: 'Intro',
  components: {
    ButtonLogin,
  },
  methods: {
  },
};
</script>

<style scoped>
#main {
  position: relative;
  width: 100%;
}
.text {
  font-family: verdana;
  font-size: 12em;
  font-weight: 700;
  color: rgb(251,247,231);
  text-shadow:
    1px 1px 13px rgb(251,195,107),
    1px 2px 3px rgb(251,195,107),
    1px 3px 1px rgb(251,195,107),
    1px 4px 3px rgb(251,195,107),
    1px 5px 1px rgb(251,195,107),
    1px 6px 3px rgb(251,195,107),
    1px 7px 3px rgb(251,195,107),
    1px 8px 3px rgb(251,195,107),
    1px 9px 1px rgb(251,195,107),
    1px 10px 3px rgb(251,195,107),
    1px 18px 6px rgba(16, 16, 16, 0.4),
    1px 22px 10px rgba(16, 16, 16, 0.2),
    1px 25px 35px rgba(16, 16, 16, 0.2);
}
.text:hover {
  cursor: pointer;
  text-shadow:
    1px 1px 13px rgb(251,195,107),
    1px 2px 1px rgb(251,195,107),
    1px 3px 1px rgb(251,195,107),
    1px 4px 1px rgb(251,195,107),
    1px 5px 1px rgb(251,195,107),
    1px 5px 1px rgb(251,195,107),
    1px 7px 1px rgb(251,195,107),
    1px 8px 1px rgb(251,195,107),
    1px 9px 1px rgb(251,195,107),
    1px 10px 1px rgb(251,195,107),
    1px 18px 6px rgba(16, 16, 16, 0.4),
    1px 22px 10px rgba(16, 16, 16, 0.2),
    1px 25px 35px rgba(16, 16, 16, 0.2),
    1px 30px 60px rgba(16, 16, 16, 0.4);
}

#loginBox > html {
  overflow-y: hidden !important;
}

#loginBox {
  margin-top: 15vh;
}

#loginBox >>> .abcRioButtonBlue {
  background-color: rgba(255, 255, 255, 0.7) !important;
  color:black !important;
}

/* .btn-enter-service {
  border: solid;
  margin: 1em;
  min-width: 40vw;
  min-height: 5em;
}
.btn-enter-service span {
  padding: 0.5em;
  font-size: 2em;
  color: black;
}
.btn-enter-service span:hover {
  cursor: pointer;
} */
</style>
