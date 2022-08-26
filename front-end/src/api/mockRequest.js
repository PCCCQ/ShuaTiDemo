import axios from "axios";
// 引入进度条组件
import nprogress from "nprogress";
import 'nprogress/nprogress.css'


//1、对axios二次封装
const requests = axios.create({
    //基础路径，requests发出的请求在端口号后面会跟改baseURl
    baseURL:'/mock',
    timeout: 5000,
})
//2、配置请求拦截器
requests.interceptors.request.use(config => {
    //config内主要是对请求头Header配置
    //比如添加token
    // 开始进度条组件
    nprogress.start();
    return config;
})
//3、配置相应拦截器
requests.interceptors.response.use((res) => {
    // 结束组件
    nprogress.done()
    //成功的回调函数
    return  res.data;
},(error) => {
    //失败的回调函数
    console.log("响应失败"+error)
    return Promise.reject(new Error('fail'))
})
//4、对外暴露
export default requests;