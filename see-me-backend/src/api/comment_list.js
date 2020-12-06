import fetch from '@/axios'

export function list (params) {
    return fetch({
        url: '/admin/user/comment/list',
        method: 'post',
        data: params
    })
}
export function save (params) {
    return fetch({
        url: '/admin/user/comment/save',
        method: 'post',
        data: params
    })
}
export function remove (id) {
    return fetch({
        url: '/admin/user/comment/remove/' + id,
        method: 'get'
    })
}
export function findAllProjectList () {
    return fetch({
        url: '/admin/user/comment/findAllProjectList',
        method: 'get'
    })
}
