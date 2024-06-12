/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-16 01:26:34
 */
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'
import './assets/css/index.scss'
import ElementUI from 'element-ui'
import './assets/font2/iconfont.css'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/js/iconfont.js'
import '@/assets/js/iconfont1.js'
import '@/assets/js/iconfont2.js'
import '@/assets/js/iconfont3.js'

Vue.use(ElementUI)

Vue.config.productionTip = false


//runtime

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
