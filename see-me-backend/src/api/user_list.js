import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/user/list',
        method: 'post',
        data: params
    })
}
export function save(params) {
    return fetch({
        url: '/admin/user/user/save',
        method: 'post',
        data: params
    })
}

export function update(params) {
    return fetch({
        url: '/admin/user/user/update',
        method: 'post',
        data: params
    })
}
export function remove(id) {
    return fetch({
        url: '/admin/user/user/remove/' + id,
        method: 'get'
    })
}

export function changeFreeze(params) {
    return fetch({
        url: '/admin/user/user/changeFreeze',
        data: params,
        method: 'post'
    })
}
export function changeStop(params) {
    return fetch({
        url: '/admin/user/user/changeStop',
        data: params,
        method: 'post'
    })
}
export function followingsList(params) {
    return fetch({
        url: '/admin/user/user/followingslist',
        data: params,
        method: 'post'
    })
}
export function followersList(params) {
    return fetch({
        url: '/admin/user/user/followerslist',
        data: params,
        method: 'post'
    })
}
