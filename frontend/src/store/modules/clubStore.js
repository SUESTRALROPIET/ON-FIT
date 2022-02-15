import { apiInstance } from '@/api/index';

const clubStore = {
  namespace: true,
  state: {
    storeClubList: [],
  },
  mutations: {
    getClubList(state, clubList) {
      state.storeClubList = clubList;
    },
  },
  actions: {
    getClubList({ commit }) {
      const api = apiInstance();
      api.get('/club')
        .then((response) => {
          commit('getClubList', response.data.ClubList);
        });
    },
  },
};

export default clubStore;
