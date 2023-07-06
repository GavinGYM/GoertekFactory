import request from '@/utils/request'

// 查询报工列表
export function listReport(query) {
  return request({
    url: '/mes/report/list',
    method: 'get',
    params: query
  })
}

// 查询报工详细
export function getReport(reportId) {
  return request({
    url: '/mes/report/' + reportId,
    method: 'get'
  })
}

// 新增报工
export function addReport(data) {
  return request({
    url: '/mes/report',
    method: 'post',
    data: data
  })
}

// 修改报工
export function updateReport(data) {
  return request({
    url: '/mes/report',
    method: 'put',
    data: data
  })
}

// 删除报工
export function delReport(reportId) {
  return request({
    url: '/mes/report/' + reportId,
    method: 'delete'
  })
}
