import request from '@/utils/request'

// 查询测评任务列表
export function listTestTask(query) {
  return request({
    url: '/bid/testTask/list',
    method: 'get',
    params: query
  })
}

// 查询测评任务详细
export function getTestTask(id) {
  return request({
    url: '/bid/testTask/' + id,
    method: 'get'
  })
}

// 新增测评任务
export function addTestTask(data) {
  return request({
    url: '/bid/testTask',
    method: 'post',
    data: data
  })
}

// 修改测评任务
export function updateTestTask(data) {
  return request({
    url: '/bid/testTask',
    method: 'put',
    data: data
  })
}

// 删除测评任务
export function delTestTask(id) {
  return request({
    url: '/bid/testTask/' + id,
    method: 'delete'
  })
}

// 导出测评任务
export function exportTestTask(query) {
  return request({
    url: '/bid/testTask/export',
    method: 'get',
    params: query
  })
}