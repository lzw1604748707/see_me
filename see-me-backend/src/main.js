// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import moment from 'moment'
import { VueEditor } from "vue2-editor";
import './permission' // 权限

Vue.config.productionTip = false

Vue.use(VueEditor)
Vue.component("VueEditor", VueEditor);
Vue.prototype.$moment = moment;
/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    components: { App }
})
