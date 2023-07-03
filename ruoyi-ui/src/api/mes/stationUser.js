import request from '@/utils/request'

// 查询工位绑定列表
export function listStationUser(query) {
  return request({
    url: '/mes/stationUser/list',
    method: 'get',
    params: query
  })
}

// 查询工位绑定详细
export function getStationUser(stationUserId) {
  return request({
    url: '/mes/stationUser/' + stationUserId,
    method: 'get'
  })
}

// 新增工位绑定
export function addStationUser(data) {
  return request({
    url: '/mes/stationUser',
    method: 'post',
    data: data
  })
}

// 修改工位绑定
export function updateStationUser(data) {
  return request({
    url: '/mes/stationUser',
    method: 'put',
    data: data
  })
}

// 删除工位绑定
export function delStationUser(stationUserId) {
  return request({
    url: '/mes/stationUser/' + stationUserId,
    method: 'delete'
  })
}
