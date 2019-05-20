import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/info/information/list',
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
export function downShelf(id) {
    return fetch({
        url: '/admin/info/information/downShelf/' + id,
        method: 'get'
    })
}
export function upShelf(id) {
    return fetch({
        url: '/admin/info/information/upShelf/' + id,
        method: 'get'
    })
}


export function getSchoolList(params) {
    return fetch({
        url: '/admin/info/information/getSchoolList/',
        method: 'post',
        data: params
    })
}
