import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/info/information/list',
        method: 'post',
        data: params
    })
}

export function findById(params) {
    return fetch({
        url: '/admin/info/information/findById',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/info/information/remove/' + id,
        method: 'get'
    })
}
export function save(params) {
    return fetch({
        url: '/admin/info/information/save',
        method: 'post',
        data: params
    })
}
export function update(params) {
    return fetch({
        url: '/admin/info/information/update',
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

