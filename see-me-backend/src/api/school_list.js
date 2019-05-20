import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/school/list',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/user/school/delete/' + id,
        method: 'get'
    })
}
export function save(params) {
    return fetch({
        url: '/admin/user/school/save',
        method: 'post',
        data: params
    })
}
export function update(params) {
    return fetch({
        url: '/admin/user/school/update',
        method: 'post',
        data: params
    })
}
export function stop(id) {
    return fetch({
        url: '/admin/user/school/stop/' + id,
        method: 'get'
    })
}
export function enable(id) {
    return fetch({
        url: '/admin/user/school/enable/' + id,
        method: 'get'
    })
}
export function resetLeaderPhone(id) {
    return fetch({
        url: '/admin/user/school/resetLeaderPhone/' + id,
        method: 'get'
    })
}
