import request from '@/utils/request'

// 查询工序设置列表
export function listProcess(query) {
  return request({
    url: '/mes/process/list',
    method: 'get',
    params: query
  })
}

// 查询工序设置详细
export function getProcess(processId) {
  return request({
    url: '/mes/process/' + processId,
    method: 'get'
  })
}

// 新增工序设置
export function addProcess(data) {
  return request({
    url: '/mes/process',
    method: 'post',
    data: data
  })
}

// 修改工序设置
export function updateProcess(data) {
  return request({
    url: '/mes/process',
    method: 'put',
    data: data
  })
}

// 删除工序设置
export function delProcess(processId) {
  return request({
    url: '/mes/process/' + processId,
    method: 'delete'
  })
}
