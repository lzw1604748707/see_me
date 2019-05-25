import fetch from '@/axios'

export function platformCount() {
    return fetch({
        url: '/admin/dashBoard/platformCount',
        method: 'get'
    })
}

export function historyOnline() {
    return fetch({
        url: '/admin/dashBoard/historyOnline',
        method: 'get'
    })
}
