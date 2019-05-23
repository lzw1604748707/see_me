import axios from 'axios'
import { getToken } from '@/utils/token'
import Vue from 'vue'
import qs from 'qs'

axios.defaults.timeout = 10000 // 响应nrpm时间
    axios.defaults.baseURL = '/api' // 配置接口地址
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.defaults.withCredentials = true

// 创建axios实例
// request拦截器
axios.interceptors.request.use(config => {
    // Do something before request is sent
    if (getToken()) {
        // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
        config.headers['jxtAdminSessionId'] = getToken()
    }
    // 在发送请求之前做某件事
    if (config.method === 'post') {
        config.data = qs.stringify(config.data)
    }
    return config
}, error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
})

axios.interceptors.response.use((res) => {
    console.log(res.data)
        // 对响应数据做些事
    if (res.data.code === '501') {
        // 业务失败
        Vue.$message.warning(res.data.msg)
        return Promise.reject(res.data.msg)
    } else if (res.data.code === '401') {
        location.href = "/401";
        //无权访问
    } else if (res.data.code === 10001 || res.data.code === 10002) {
        Promise.reject(res.data.msg)
    } else {
        return res
    }
}, (error) => {
    console.log(error) // for debug
    return Promise.reject(error)
})

export default axios
