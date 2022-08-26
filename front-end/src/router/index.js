// 引入路由
import Vue from 'vue'
import VueRouter from 'vue-router'
import { getStorage } from '../utils/Storage'
import store from '../store/index'
// 使用路由插件
Vue.use(VueRouter);

let router = new VueRouter({
    routes: [
        {
            // 主页面组件
            path: '/home',
            component: () => import('../pages/Home'),
            // meta: { uuid: getStorage('uuid') },
            name: 'home'
        },
        {
            // 全部题库
            path: '/allbank',
            component: () => import('../pages/AllBank'),
            // meta: { show: true },路由元信息
            name: 'allbank'
        },
        {
            // 我的题库组件
            path: '/questionbank',
            component: () => import('../pages/Questionbank'),
            // meta: { show: true },路由元信息
            name: 'questionbank'
        },
        {
            // 上传题库组件
            path: '/upload',
            component: () => import('../pages/Upload'),
            // meta: { show: true },路由元信息
            name: 'upload'
        },
        // {
        //     // 手动输入题库
        //     path: '/input',
        //     component: () => import('../pages/Input'),
        //     // meta: { show: true },路由元信息
        //     name: 'input'
        // },
        {
            // 我的考试页面
            path: '/exam/:bankid',
            component: () => import('../pages/Exam'),
            meta: { showAside: true },
            name: 'exam'
        },
        {
            // 我的错题组件
            path: '/wrong',
            component: () => import('../pages/WrongQuestion'),
            // meta: { show: true },路由元信息
            name: 'wrong'
        },
        {
            // 模拟考试组件
            path: '/examination',
            component: () => import('../pages/Examination'),
            // meta: { show: true },路由元信息
            name: 'examination'
        },
        {
            // 反馈建议
            path: '/callbug',
            component: () => import('../pages/CallBug'),
            // meta: { show: true },路由元信息
            name: 'callbug'
        },
        {
            // 登录和注册
            path: '/login',
            component: () => import('../pages/Login'),
            meta: { isLogin: false },
            name: 'login'
        },{
            // 背题界面
            path: '/practice/:bankid',
            component: () => import('../pages/Practice'),
            meta: { showAside: true },
            name: 'practice'
        },
        // 访问时直接定位到主页面（页面重定向）
        {
            path: '*',
            redirect: '/home'
        }

    ]
})

router.beforeEach((to, from, next) => {
    if (store.state.uuid) {
        if (to.path == "/login") 
        { next('/Home') }else{
            next();
        }
    } else {
        if (to.path == '/home' || to.path == '/allbank' || to.path == "/login"||to.path=='/callbug') 
        { next(); }else{
            next("/")
        }
    }
})






// 暴露路由组件
export default router;