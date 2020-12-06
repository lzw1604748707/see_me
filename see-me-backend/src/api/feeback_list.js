import fetch from '@/axios'

export function list (params) {
    return fetch({
        url: '/admin/feeback/list',
        method: 'post',
        data: params
    })
}

export function save (params) {
    return fetch({
        url: '/admin/feeback/save',
        method: 'post',
        data: params
    })
}

export function remove (id) {
    return fetch({
        url: '/admin/feeback/remove/' + id,
        method: 'get'
    })
}

export function reSaveStatus (params) {
    return fetch({
        url: '/admin/feeback/changeStatus',
        data: params,
        method: 'post'
    })
}
