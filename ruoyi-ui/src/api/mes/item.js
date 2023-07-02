import request from '@/utils/request'

// 查询物料信息列表
export function listItem(query) {
  return request({
    url: '/mes/item/list',
    method: 'get',
    params: query
  })
}

// 查询物料信息详细
export function getItem(materialItemId) {
  return request({
    url: '/mes/item/' + materialItemId,
    method: 'get'
  })
}

// 新增物料信息
export function addItem(data) {
  return request({
    url: '/mes/item',
    method: 'post',
    data: data
  })
}

// 修改物料信息
export function updateItem(data) {
  return request({
    url: '/mes/item',
    method: 'put',
    data: data
  })
}

// 删除物料信息
export function delItem(materialItemId) {
  return request({
    url: '/mes/item/' + materialItemId,
    method: 'delete'
  })
}
