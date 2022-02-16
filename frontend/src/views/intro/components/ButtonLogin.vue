<template>
  <html lang="en">
  <body>
    <div id="my-signin2"></div>
  </body>
</html>
</template>

<script>
import Vue from 'vue';
import Vuex, { mapGetters, mapMutations } from 'vuex';
import axios from 'axios';

const userStore = 'userStore';

Vue.use(Vuex);

export default ({
  namespaced: true,
  name: 'ButtonLogin',
  data() {
    return {
      googleUser: null,
    };
  },
  mounted() {
    window.gapi.signin2.render('my-signin2', {
      scope: 'profile email',
      width: 300,
      height: 65,
      longtitle: true,
      theme: 'dark',
      onsuccess: this.onSuccess,
      onfailure: this.onFailure,
    });
  },
  methods: {
    ...mapMutations(userStore, [
      'setLoginStatus',
      'setUserId',
    ]),
    ...mapGetters(userStore, [
      'getLoginStatus',
      'getUserId',
    ]),
    onSuccess(googleUser) {
      this.googleUser = googleUser.getBasicProfile();
      const userEmail = googleUser.getBasicProfile().getEmail();
      const userId = userEmail.substring(0, userEmail.indexOf('@'));
      const userName = googleUser.getBasicProfile().getName();

      this.setLoginStatus(true);
      this.setUserId(userId);

      console.log('로그인 후');
      console.log(this.getLoginStatus());
      console.log(this.getUserId());

      const googleLoginReq = {
        userId,
        userName,
      };
      axios.post('http://localhost:8081/api/mypage', googleLoginReq, {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-type': 'application/json',
        },
      })
        .then((response) => {
          console.log(response);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onFailure(error) {
      // eslint-disable-next-line
      console.log(error);
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
