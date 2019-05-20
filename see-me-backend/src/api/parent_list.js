import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/parent/list',
        method: 'post',
        data: params
    })
}
export function schoolList() {
    return fetch({
        url: '/admin/user/parent/schoolList',
        method: 'get'
    })
}
