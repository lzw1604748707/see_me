import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/info/adv/list',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/info/adv/remove/' + id,
        method: 'get'
    })
}
export function save(params) {
    return fetch({
        url: '/admin/info/adv/save',
        method: 'post',
        data: params
    })
}
export function update(params) {
    return fetch({
        url: '/admin/info/adv/update',
        method: 'post',
        data: params
    })
}
export function findCreateFileldList() {
    return fetch({
        url: '/admin/user/project/findCreateFileldList',
        method: 'get'
    })
}

