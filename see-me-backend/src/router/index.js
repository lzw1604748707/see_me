import Vue from 'vue'
import Router from 'vue-router'
import Layout from '../components/Layout.vue'

import Login from '../views/login/Login.vue'



Vue.use(Router)


export const constantRouterMap = [{
        path: '/admin',
        component: Layout,
        name: '首页',
        icon: 'el-icon-menu',
        redirect: '/admin/dashboard',
        noDropdown: true,
        children: [{
            path: 'dashboard',
            component: resolve => require(['../views/dashboard'], resolve),
            name: '首页'
        }]
    }, {
        path: '/admin/login',
        name: '登陆',
        hidden: true,
        component: Login
    },
    { path: '/404', component: resolve => require(['../views/error/404.vue'], resolve), hidden: true },
    { path: '/401', component: resolve => require(['../views/error/401.vue'], resolve), hidden: true }
]

export const asyncRouterMap = [{
    path: '/admin/user',
    component: Layout,
    name: '用户管理',
    redirect: "noredirect",
    icon: 'el-icon-date',
    children: [
        {
            path: 'account',
            name: '用户列表',
            component: resolve => require(['../views/user/AccountList.vue'], resolve)
        },
        {
            path: 'project',
            name: '项目列表',
            component: resolve => require(['../views/user/projectList.vue'], resolve)
        },
        {
            path: 'comment',
            name: '评论监管',
            component: resolve => require(['../views/user/commentList.vue'], resolve)
        },
        {
            path: 'collection',
            name: '作品集列表',
            component: resolve => require(['../views/user/collectionList.vue'], resolve)
        },
        {
            path: 'createfield',
            name: '创作领域列表',
            component: resolve => require(['../views/user/createFieldList.vue'], resolve)
        }
    ]
}, {
    path: '/admin/info',
    component: Layout,
    name: '信息管理',
    redirect: "noredirect",
    icon: 'el-icon-picture',
    children: [{
            path: 'information',
            name: '资讯列表',
            component: resolve => require(['../views/info/InfoList.vue'], resolve)
        },
        {
            path: 'adv',
            name: '广告列表',
            component: resolve => require(['../views/info/AdvList.vue'], resolve)
        }
    ]
}, {
    path: '/admin/feedback',
    component: Layout,
    name: '意见反馈',
    redirect: "noredirect",
    icon: 'el-icon-message',
    children: [{
        path: 'list',
        name: '意见列表',
        component: resolve => require(['../views/feeback/FeebackList.vue'], resolve)
    }]
}, {
    path: '/admin/auth',
    component: Layout,
    name: '管理员管理',
    redirect: "noredirect",
    icon: 'el-icon-setting',
    children: [{
            path: 'account',
            name: '管理员列表',
            component: resolve => require(['../views/auth/AccountList.vue'], resolve)
        },
        {
            path: 'role',
            name: '权限管理',
            component: resolve => require(['../views/auth/RoleList.vue'], resolve)
        }
    ]
}, {
    path: '*',
    name: '404',
    redirect: '/404',
    hidden: true
}]

export default new Router({
    scrollBehavior: () => ({
        y: 0
    }),
    mode: 'history',
    routes: constantRouterMap
})
