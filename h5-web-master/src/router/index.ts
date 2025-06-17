import { createRouter, createWebHashHistory,createWebHistory } from "vue-router";
const routes = [
  
    {
        path: '/',
        name:"home",
        component: () => import("@/views/Home.vue")
    },
    {
        path: '/category',
        name:"category",
        component: () => import("@/views/product/Category.vue")
    },
    {
        path: '/product/list',
        name:"product-list",
        component: () => import("@/views/product/ProductList.vue")
    },
    {
        path: '/product/detail',
        name:"product-detail",
        component: () => import("@/views/product/ProductDetail.vue")
    },
    {
        path: '/finder',
        name:"finder",
        component: () => import("@/views/Finder.vue")
    },
    {
        path: '/cart',
        name:"cart",
        component: () => import("@/views/Cart.vue")
    },
    {
        path: '/my',
        name:"my",
        component: () => import("@/views/user/My.vue")
    },
    {
        path: '/user/regist',
        name:"user-regist",
        component: () => import("@/views/user/Register.vue")
    },
    {
        path: '/user/login',
        name:"user-login",
        component: () => import("@/views/user/Login.vue")
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
