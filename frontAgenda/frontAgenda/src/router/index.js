import { createRouter, createWebHistory } from "vue-router"
import Login from "../views/Index.vue"
import Cadastro from "../views/Cadastro.vue"
import Inicio from "../views/Inicio.vue"

const routes = [
  { path: "/", component: Login },
  { path: "/cadastro", component: Cadastro },
  { 
    path: "/inicio",
    component: Inicio,
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Proteção de rota
router.beforeEach((to, from, next) => {
  const logado = localStorage.getItem("usuarioId")

  if (to.meta.requiresAuth && !logado) {
    return next("/")
  }

  next()
})

export default router