import fetch from '@/axios'

export function list(params) {
    return fetch({
        url: '/admin/user/student/list',
        method: 'post',
        data: params
    })
}
export function schoolList() {
    return fetch({
        url: '/admin/user/student/schoolList',
        method: 'get'
    })
}
export function gradeList(params) {
    return fetch({
        url: '/admin/user/student/gradeList',
        method: 'post',
        data: params
    })
}
