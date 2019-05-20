import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/feeback/list',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/feeback/remove/' + id,
        method: 'get'
    })
}
