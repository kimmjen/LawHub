import { createRouter, createWebHistory } from 'vue-router';

// 라우트 정의
const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../App.vue')
  },
  // 필요한 경우 추가 라우트들을 여기에 정의
  // {
  //   path: '/suits',
  //   name: 'Suits',
  //   component: () => import('../views/Suits.vue')
  // },
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: () => import('../views/Login.vue')
  // }
];

// 라우터 인스턴스 생성
const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;