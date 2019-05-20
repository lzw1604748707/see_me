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
export function downShelf(id) {
    return fetch({
        url: '/admin/info/adv/downShelf/' + id,
        method: 'get'
    })
}
export function upShelf(id) {
    return fetch({
        url: '/admin/info/adv/upShelf/' + id,
        method: 'get'
    })
}


export function getSchoolList(params) {
    return fetch({
        url: '/admin/info/adv/getSchoolList/',
        method: 'post',
        data: params
    })
}
