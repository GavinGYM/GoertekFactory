import request from '@/utils/request'

// 查询工位信息列表
export function listStation(query) {
  return request({
    url: '/mes/station/list',
    method: 'get',
    params: query
  })
}

// 查询工位信息详细
export function getStation(stationId) {
  return request({
    url: '/mes/station/' + stationId,
    method: 'get'
  })
}

// 新增工位信息
export function addStation(data) {
  return request({
    url: '/mes/station',
    method: 'post',
    data: data
  })
}

// 修改工位信息
export function updateStation(data) {
  return request({
    url: '/mes/station',
    method: 'put',
    data: data
  })
}

// 删除工位信息
export function delStation(stationId) {
  return request({
    url: '/mes/station/' + stationId,
    method: 'delete'
  })
}
