import request from '@/utils/request'

// 查询工序路线列表
export function listRoute(query) {
  return request({
    url: '/mes/route/list',
    method: 'get',
    params: query
  })
}

// 查询工序路线详细
export function getRoute(routeId) {
  return request({
    url: '/mes/route/' + routeId,
    method: 'get'
  })
}

// 新增工序路线
export function addRoute(data) {
  return request({
    url: '/mes/route',
    method: 'post',
    data: data
  })
}

// 修改工序路线
export function updateRoute(data) {
  return request({
    url: '/mes/route',
    method: 'put',
    data: data
  })
}

// 删除工序路线
export function delRoute(routeId) {
  return request({
    url: '/mes/route/' + routeId,
    method: 'delete'
  })
}
