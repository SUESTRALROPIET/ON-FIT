import Vue from 'vue';
import Vuex from 'vuex';

import personalStore from '@/store/modules/personalStore';
import accountsStore from '@/store/modules/accountsStore';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    personalStore,
    accountsStore,
  },
});
