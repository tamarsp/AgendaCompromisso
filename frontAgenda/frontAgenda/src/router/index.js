/*import { createRouter, createWebHistory } from 'vue-router'
import index from '@/pasta/index.vue'
import cadastro from '@/pasta/cadastro.vue'
import inicio from '@/pasta/inicio.vue'

const routes = [
  { path: '/', component: index },
  { path: '/cadastro', component: cadastro },
  { path: '/inicio', component: inicio }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})*/

/*export default router*/
import { createRouter, createWebHashHistory } from 'vue-router'
import index from '@/pasta/index.vue'
import cadastro from '@/pasta/cadastro.vue'
import inicio from '@/pasta/inicio.vue'

const routes = [
  { path: '/', component: index },
  { path: '/cadastro', component: cadastro },
  { path: '/inicio', component: inicio }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
