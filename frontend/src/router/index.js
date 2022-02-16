import Vue from 'vue';
import VueRouter from 'vue-router';

import Intro from '@/views/intro/Intro.vue';
import Main from '@/views/main/Main.vue';

import Personal from '@/views/personal/Personal.vue';
import TodayEx from '@/views/personal/TodayEx.vue';
import PersonalTraining from '@/views/personal/PersonalTraining.vue';

import Club from '@/views/club/Club.vue';
import ClubTraining from '@/views/club/ClubTraining.vue';

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
    path: '/personal',
    name: 'PersonalTraining',
    component: PersonalTraining,
  },
  {
    path: '/personal',
    name: 'TodayEx',
    component: TodayEx,
  },
  {
    path: '/club',
    name: 'Club',
    component: Club,
  },
  {
    path: '/club/ClubTraining',
    name: 'ClubTraining',
    component: ClubTraining,
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
