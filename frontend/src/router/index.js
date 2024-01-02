// Composables
import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Registration from "@/views/Registration.vue";
import Directory from "@/views/UserDirectories/Directory.vue";

const routes = [
  {
    path: '/',
    name: "Directory",
    component: Directory
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
  console.log('TEST ROUTER')
  next()
})

export default router
