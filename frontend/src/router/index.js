import Vue from 'vue';
import VueRouter from 'vue-router';

import Intro from '@/views/intro/Intro.vue';
import Main from '@/views/main/Main.vue';
import Personal from '@/views/personal/Personal.vue';
import Club from '@/views/club/Club.vue';
import MyPage from '@/views/mypage/MyPage.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Intro',
    component: Intro,
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
  },
  {
    path: '/personal',
    name: 'Personal',
    component: Personal,
  },
  {
    path: '/club',
    name: 'Club',
    component: Club,
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
