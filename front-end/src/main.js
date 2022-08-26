import Vue from 'vue'
import App from './App.vue'
import '../public/index.css'
// 饿了么组件库

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './mock/index'// 引入假数据
// import { Pagination } from 'element-ui';
// Vue.use(Pagination)
Vue.use(ElementUI);
// 注册全局组件
import MySteps from '../src/components/MySteps'
Vue.component('MySteps', MySteps)
// 引入路由
import router from './router'

// 引入vuex
import store from './store/index'
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  // 注册路由
  router,
  store
}).$mount('#app')
