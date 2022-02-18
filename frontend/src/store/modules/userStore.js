const userStore = {
  namespaced: true,
  state: {
    loggedIn: false,
    userId: '',
  },
  getters: {
    getLoginStatus(state) {
      return state.loggedIn;
    },
    getUserId(state) {
      return state.userId;
    },
  },
  mutations: {
    setLoginStatus(state, payload) {
      state.loggedIn = payload;
    },
    setUserId(state, payload) {
      state.userId = payload;
    },
  },
  actions: {
  },
};

export default userStore;
