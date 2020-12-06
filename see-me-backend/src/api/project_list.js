import fetch from '@/axios'

export function list (params) {
    return fetch({
        url: '/admin/user/project/list',
        method: 'post',
        data: params
    })
}
export function findById (params) {
    return fetch({
        url: '/admin/user/project/findById',
        method: 'post',
        data: params
    })
}
export function save (params) {
    return fetch({
        url: '/admin/user/project/save',
        method: 'post',
        data: params
    })
}

export function update (params) {
    return fetch({
        url: '/admin/user/project/update',
        method: 'post',
        data: params
    })
}
export function remove (id) {
    return fetch({
        url: '/admin/user/project/remove/' + id,
        method: 'get'
    })
}


export function changeStatus (params) {
    return fetch({
        url: '/admin/user/project/changeStatus',
        data: params,
        method: 'post'
    })
}
export function findCreateFileldList () {
    return fetch({
        url: '/admin/user/project/findCreateFileldList',
        method: 'get'
    })
}

