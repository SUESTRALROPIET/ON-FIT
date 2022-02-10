import Vue from 'vue';
import Vuex from 'vuex';

import personalStore from '@/store/modules/personalStore';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    personalStore,
  },
});
