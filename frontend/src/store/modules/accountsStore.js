// import axios from 'axios';
import { apiInstance } from '@/api/index';

const api = apiInstance();
const accountsStore = {
  namespace: true,
  state: {
    exDays: [],
    exTimes: 0,
    exCals: 0,
  },
  getters: {

  },
  mutations: {
    GET_EX_DAYS(state, arr) {
      state.exDays = arr;
    },
    GET_EX_TIMES(state, val) {
      state.exTimes = val;
    },
    GET_EX_CALS(state, val) {
      state.exCals = val;
    },
  },
  actions: {
    // TODO: 로그인 시점에 실행시키기
    getExDays({ commit }, userId) {
      function ddmmyyyy(dateTime) {
        const result1 = dateTime.split('-');
        const result2 = result1[2].split('T');
        const date = `${Number(result2[0])}/${Number(result1[1])}/${result1[0]}`;
        return date;
      }
      // axios.get(`http://localhost:8081/mypage/${userId}`)
      api.get(`/mypage/${userId}`)
        .then((res) => {
          // 데이터 중 날짜만 필터링
          const result = res.data.MyExerciseLog;
          const exDate = result.map((v) => ddmmyyyy(String(v.exTime)));
          commit('GET_EX_DAYS', Array.from(new Set(exDate)));
        });
    },
    getTime({ commit }, userId) {
      // axios.get(`http://localhost:8081/mypage/${userId}`)
      api.get(`/mypage/${userId}`)
        .then((res) => {
          const result = res.data.MyExerciseLog;
          const sec = result.map((v) => v.exDuration).reduce((sum, val) => sum + val, 0);
          const hour = parseInt(sec / 3600, 10);
          const min = parseInt((sec % 3600) / 60, 10);
          const exTime = `${hour}h ${min}m`;
          commit('GET_EX_TIMES', exTime);
        });
    },
    getCal({ commit }, userId) {
      // axios.get(`http://localhost:8081/mypage/${userId}`)
      api.get(`/mypage/${userId}`)
        .then((res) => {
          const result = res.data.MyExerciseLog;
          const exCal = result.map((v) => v.exCal).reduce((sum, val) => sum + val, 0);
          commit('GET_EX_CALS', exCal);
        });
    },
  },
};

export default accountsStore;
