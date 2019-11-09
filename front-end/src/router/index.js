import Vue from 'vue'
import Router from 'vue-router'
import Service from '@/components/Service'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Service',
      component: Service
    }
  ]
})
