<template>
  <v-app>
    <div
      id="navbar"
      class="d-flex flex-row justify-space-between align-center"
    >
      <router-link to="/main">
        <img src="@/assets/logo.png" alt="logo-image" height="72px">
      </router-link>
      <div class="d-flex">
        <router-link to="/personal" id="router-link-list">개인운동</router-link>
        <p id="router-link-divider">|</p>
        <router-link to="/club" id="router-link-list">단체운동</router-link>
        <p id="router-link-divider">|</p>
        <router-link to="/mypage" id="router-link-list">My Page</router-link>
      </div>
      <div id="logout-link">
        <p class="my-auto" @click="signOut" v-if="this.getLoginStatus()">Logout</p>
      </div>
    </div>
    <v-main>
      <div id="view-page">
        <router-view/>
      </div>
    </v-main>
  </v-app>
</template>

<script>

import Vue from 'vue';
import Vuex, { mapGetters, mapMutations } from 'vuex';

const userStore = 'userStore';
Vue.use(Vuex);

export default {
  name: 'App',
  namespaced: true,
  data: () => ({
  }),
  methods: {
    ...mapMutations(userStore, [
      'setLoginStatus',
      'setUserId',
    ]),
    ...mapGetters(userStore, [
      'getLoginStatus',
      'getUserId',
    ]),
    signOut() {
      console.log('로그아웃');
      const authInst = window.gapi.auth2.getAuthInstance();
      authInst.signOut().then(() => {
        // eslint-disable-next-line
        console.log('User Signed Out!!!');
      });
      this.setLoginStatus(false);
      this.setUserId('');

      console.log(this.getLoginStatus());
      console.log(this.getUserId());
    },
  },
};

</script>

<style scoped>
#app {
  background: url('./assets/background.png')
  no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
#navbar {
  padding: 0px 80px;
  height: 80px;
}
#router-link-list {
  text-decoration: none;
  color: black;
  margin: 0px 40px 0px 0px;
}
.router-link-active {
  font-weight: bold;
}
#router-link-divider {
  margin: 0px 40px 0px 0px;
}
#logout-link {
  color: white;
}
#view-page {
  margin: 40px 80px 80px 80px;
}
</style>
