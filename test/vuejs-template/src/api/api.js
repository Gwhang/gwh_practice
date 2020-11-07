import http from '../axios'

export default {
  getOtp(telephone) {
    return http('post', '/api/user/getotp', { telephone: telephone })
  },
  register(user) {
    return http('post', '/api/user/register', user)
  },
  login(telephone, password) {
    return http('post', '/api/user/login', { telephone: telephone, password: password })
  },
  createItem(goods) {
    return http('post', '/api/item/create', goods)
  },
  getGoodsList() {
    return http('get', '/api/item/list')
  },
  getGoodsDetail(id) {
    return http('get', '/api/item/get', { id: id })
  },
  createOrder(order){
    return http('post', '/api/order/createOrder', order)
  }
}