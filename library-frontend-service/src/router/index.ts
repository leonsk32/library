import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/books',
    name: 'books',
    component: () => import(/* webpackChunkName: "about" */ '../views/Books.vue'),
  },
  {
    path: '/search',
    name: 'search',
    component: () => import(/* webpackChunkName: "search" */ '../views/Search.vue'),
  },
  {
    path: '/ranking',
    name: 'ranking',
    component: () => import(/* webpackChunkName: "ranking" */ '../views/Ranking.vue'),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
