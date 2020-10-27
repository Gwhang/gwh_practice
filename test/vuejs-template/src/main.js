import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'

import './common/css/index.less'

import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

//在应用启动时，可以通过设置 Vue.config.productionTip = false 来关闭生产模式下给出的提示
Vue.config.productionTip = true

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    render: h => h(App)
})

