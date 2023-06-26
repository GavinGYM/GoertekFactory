import request from '@/utils/request'

// 查询车间信息列表
export function listWorkshop(query) {
  return request({
    url: '/mes/workshop/list',
    method: 'get',
    params: query
  })
}

// 查询车间信息详细
export function getWorkshop(workshopId) {
  return request({
    url: '/mes/workshop/' + workshopId,
    method: 'get'
  })
}

// 新增车间信息
export function addWorkshop(data) {
  return request({
    url: '/mes/workshop',
    method: 'post',
    data: data
  })
}

// 修改车间信息
export function updateWorkshop(data) {
  return request({
    url: '/mes/workshop',
    method: 'put',
    data: data
  })
}

// 删除车间信息
export function delWorkshop(workshopId) {
  return request({
    url: '/mes/workshop/' + workshopId,
    method: 'delete'
  })
}
