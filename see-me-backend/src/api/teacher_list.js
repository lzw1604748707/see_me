import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/teacher/list',
        method: 'post',
        data: params
    })
}
export function schoolList() {
    return fetch({
        url: '/admin/user/teacher/schoolList',
        method: 'get'
    })
}
