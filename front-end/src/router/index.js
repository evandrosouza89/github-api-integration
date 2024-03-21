import { createRouter, createWebHashHistory } from 'vue-router'
import ServicePage from '../components/ServicePage.vue'

const routes = [
  { path: '/', component: ServicePage },
  { path: '/:pathMatch(.*)', redirect: '/' }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  next()
})

export default router
