import request from '@/utils/request'

// 查询物料分类列表
export function listMaterial(query) {
  return request({
    url: '/mes/material/list',
    method: 'get',
    params: query
  })
}

// 查询物料分类详细
export function getMaterial(materialId) {
  return request({
    url: '/mes/material/' + materialId,
    method: 'get'
  })
}

// 新增物料分类
export function addMaterial(data) {
  return request({
    url: '/mes/material',
    method: 'post',
    data: data
  })
}

// 修改物料分类
export function updateMaterial(data) {
  return request({
    url: '/mes/material',
    method: 'put',
    data: data
  })
}

// 删除物料分类
export function delMaterial(materialId) {
  return request({
    url: '/mes/material/' + materialId,
    method: 'delete'
  })
}
