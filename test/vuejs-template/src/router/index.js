import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const getotp = (resolve) => {
  import('../views/getotp').then((module) => {
    resolve(module)
  })
}

const register = (resolve) => {
  import('../views/register').then((module) => {
    resolve(module)
  })
}

const login = (resolve) => {
  import('../views/login').then((module) => {
    resolve(module)
  })
}

const createGoods = (resolve) => {
  import('../views/createGoods').then((module) => {
    resolve(module)
  })
}

const listGoods = (resolve) => {
  import('../views/list-goods').then((module) => {
    resolve(module)
  })
}

const goodsDetail = (resolve) => {
  import('../views/goods-detail').then((module) => {
    resolve(module)
  })
}


export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/getotp'
    },
    {
      path: '/getotp',
      component: getotp
    },
    {
      path: '/register',
      component: register
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/createGoods',
      component: createGoods
    },
    {
      path: '/listGoods',
      component: listGoods
    },
    {
      path: '/goodsDetail',
      component: goodsDetail
    }

  ]
})