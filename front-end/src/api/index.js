import mockRequsets from './mockRequest'
import requests from './requests'



// 真实接口
// 登录
export const reqLogin = (data) => requests({
    url: '/login',
    method: 'post',
    data
})

//注册
export const reqRegister = (data) => requests({
    url: '/register',
    method: 'post',
    data
})


//全部题库数据
export const reqAllBank = (data) => requests({
    url: '/allbank',
    method: 'post',
    data
})


//个人题库数据
export const reqSelfBank = (data) => requests({
    url: '/selfbank',
    method: 'post',
    data
})

// 从全部题库加入个人题库
export const reqAllToSelf = (data) => requests({
    url: '/alltoself',
    method: 'post',
    data
})


// 删除个人题库某一数据
export const reqDeleteselfdata = (data) => requests({
    url: '/deleteselfdata',
    method: 'post',
    data
})



// 添加题库
export const reqAddbank = (data) => requests({
    url: '/addbank',
    method: 'post',
    data
})

// 添加题目
export const reqAddtimu = (data) => requests({
    url: '/addtimu',
    method: 'post',
    data
})


// 背题
export const reqPractice = (data) => requests({
    url: '/practice',
    method: 'post',
    data
})


// 提交考试记录
export const reqAddExam =(data) => requests({
    url: '/addexam',
    method: 'post',
    data
})



// 查询考试记录
export const reqExamLog =(data) => requests({
    url: '/examlog',
    method: 'post',
    data
})











// 以下为模拟数据
// 题库
export const reqBank = () => mockRequsets({
    url: '/questionbank',
    method: 'GET'
})

// 错题库
export const reqWrong = () => mockRequsets({
    url: '/questionbank',
    method: 'GET'
})



// 收藏题库
export const reqCollection = () => mockRequsets({
    url: '/questionbank',
    method: 'GET'
})


