import request from '@/utils/request'

// 查询任务发布列表
export function listTask(query) {
  return request({
    url: '/bid/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务发布详细
export function getTask(id) {
  return request({
    url: '/bid/task/' + id,
    method: 'get'
  })
}

// 新增任务发布
export function addTask(data) {
  return request({
    url: '/bid/task',
    method: 'post',
    data: data
  })
}

// 修改任务发布
export function updateTask(data) {
  return request({
    url: '/bid/task',
    method: 'put',
    data: data
  })
}

// 删除任务发布
export function delTask(id) {
  return request({
    url: '/bid/task/' + id,
    method: 'delete'
  })
}

// 导出任务发布
export function exportTask(query) {
  return request({
    url: '/bid/task/export',
    method: 'get',
    params: query
  })
}