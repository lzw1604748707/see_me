import fetch from '@/axios'

export function list (params) {
    return fetch({
        url: '/admin/user/collection/list',
        method: 'post',
        data: params
    })
}
export function save (params) {
    return fetch({
        url: '/admin/user/collection/save',
        method: 'post',
        data: params
    })
}
export function update (params) {
    return fetch({
        url: '/admin/user/collection/update',
        method: 'post',
        data: params
    })
}
export function remove (id) {
    return fetch({
        url: '/admin/user/collection/remove/' + id,
        method: 'get'
    })
}
export function changeStatus (params) {
    return fetch({
        url: '/admin/user/collection/changeStatus',
        data: params,
        method: 'post'
    })
}
export function findAllProjectToCardList () {
    return fetch({
        url: '/admin/user/collection/findAllProjectToCardList',
        method: 'get'
    })
}
