import { createRouter, createWebHistory } from 'vue-router'

const routes = [
	{
	    path: '/',
	    name: 'login',
	    component: () => import('../pasta/index.vue')
	  },
	  {
	    path: '/cadastro',
	    name: 'cadastro',
	    component: () => import('../pasta/cadastro.vue')
	  },
	  {
	    path: '/inicio',
	    name: 'inicio',
	    component: () => import('../pasta/inicio.vue')
	  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router