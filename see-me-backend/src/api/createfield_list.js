import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/createfield/list',
        method: 'post',
        data: params
    })
}
export function save(params) {
    return fetch({
        url: '/admin/user/createfield/save',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/user/createfield/remove/' + id,
        method: 'get'
    })
}
export function changeStatus(params) {
    return fetch({
        url: '/admin/user/createfield/changeStatus',
        data: params,
        method: 'post'
    })
}
