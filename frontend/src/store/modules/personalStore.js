const personalStore = {
  state: {
    exTodos: [],
  },
  mutations: {
    EX_TODOS(state, exTodos) {
      state.exTodos = exTodos;
    },
  },
  actions: {
    exTodos({ commit }, exTodos) {
      commit('EX_TODOS', exTodos);
    },
  },
};

export default personalStore;
