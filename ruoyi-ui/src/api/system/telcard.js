import request from '@/utils/request'

// 查询通讯录转换记录列表
export function listTelcard(query) {
  return request({
    url: '/system/telcard/list',
    method: 'get',
    params: query
  })
}

// 查询通讯录转换记录详细
export function getTelcard(id) {
  return request({
    url: '/system/telcard/' + id,
    method: 'get'
  })
}

// 新增通讯录转换记录
export function addTelcard(data) {
  return request({
    url: '/system/telcard',
    method: 'post',
    data: data
  })
}

// 修改通讯录转换记录
export function updateTelcard(data) {
  return request({
    url: '/system/telcard',
    method: 'put',
    data: data
  })
}

// 删除通讯录转换记录
export function delTelcard(id) {
  return request({
    url: '/system/telcard/' + id,
    method: 'delete'
  })
}
