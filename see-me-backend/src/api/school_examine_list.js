import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/schoolcheck/list',
        method: 'post',
        data: params
    })
}

export function pass(id) {
    return fetch({
        url: '/admin/user/schoolcheck/pass' + id,
        method: 'get'
    })
}


export function reject(id) {
    return fetch({
        url: '/admin/user/schoolcheck/reject' + id,
        method: 'get'
    })
}


export function schoolList(id) {
    return fetch({
        url: '/admin/user/schoolcheck/schoolList',
        method: 'get'
    })
}
