<template>
  <div>
    <v-btn id="loginBtn" @click="login">
      <img src="@/assets/google.png" alt="" height="70px">
      <h2 class="my-auto">| google로 시작하기</h2>
    </v-btn>
  </div>
</template>

<script>
export default ({
  name: 'ButtonLogin',
  methods: {
    login() {
      console.log('로그인하기');
    },
  },
});
</script>

<style scoped>
#loginBtn {
  margin-top: 15rem;
  height: 65px;
  background: rgb(255, 255, 255, 0.7);
  border-radius: 30px;
}
</style>
