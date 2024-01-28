import { createRouter, createWebHistory } from 'vue-router'
import store from "@/store"
import Login from "@/views/Login.vue"
import Registration from "@/views/Registration.vue"
import FolderList from "@/views/Folders/FolderList.vue"
import FolderDetail from "@/views/Folders/FolderDetail.vue";

const routes = [
  {
    path: '/',
    name: "FolderList",
    component: FolderList
  },
  {
    path: '/folder/:id',
    name: "FolderDetail",
    component: FolderDetail
  },
  {
    path: '/login',
    name: "Login",
    component: Login
  },
  {
    path: '/registration',
    name: "Registration",
    component: Registration
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const publicPaths = ['/login', '/registration']
  const authRequired = !publicPaths.includes(to.path)
  const loggedIn = store.getters["auth/isLoggedIn"]

  if (authRequired && !loggedIn) {
    next({ path: '/login', query: { returnUrl: to.path }})
  }

  next()
})

export default router
