import Vue from "vue";
import Vuex from 'vuex'
Vue.use(Vuex);
import { getStorage} from "../utils/Storage";
// 仓库存储数据的地方 state
const state = {
uuid:getStorage('uuid')||sessionStorage.getItem('uuid'),
phone:getStorage('phone')||sessionStorage.getItem('phone'),
bankid:""
};
// 修改state的唯一手段
const mutations = {
    CHANGELOGIN(state,CHANGELOGIN){
       
        state.uuid=CHANGELOGIN.uuid
        state.phone=CHANGELOGIN.phone
    },
    CHANGEBANKID(state,CHANGEBANKID){
        state.bankid=CHANGEBANKID.bankid
    }
};
// 除了action可以书写自己的业务逻辑，也可以处理异步
const actions = {
 changeLogin({commit},data){
     commit('CHANGELOGIN',data)

 },
 changeBankid({commit},bankid){
    commit('CHANGEBANKID',bankid)
 }
};

// 理解为计算属性，用于简化仓库数据，让组件获取仓库的数据更加方便
const getters = {

};


export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters

})
