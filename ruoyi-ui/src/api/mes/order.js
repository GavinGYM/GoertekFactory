import request from '@/utils/request'

// 查询生产工单列表
export function listOrder(query) {
  return request({
    url: '/mes/order/list',
    method: 'get',
    params: query
  })
}

// 查询生产工单详细
export function getOrder(orderId) {
  return request({
    url: '/mes/order/' + orderId,
    method: 'get'
  })
}

// 新增生产工单
export function addOrder(data) {
  return request({
    url: '/mes/order',
    method: 'post',
    data: data
  })
}

// 修改生产工单
export function updateOrder(data) {
  return request({
    url: '/mes/order',
    method: 'put',
    data: data
  })
}

// 修改工单状态
export function changeOrderStatus(orderId, status) {
  return request({
    url: '/mes/order/changestatus/' + orderId + '/' + status,
    method: 'put'
  })
}

// 删除生产工单
export function delOrder(orderId) {
  return request({
    url: '/mes/order/' + orderId,
    method: 'delete'
  })
}
