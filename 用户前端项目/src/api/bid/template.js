import request from '@/utils/request'

// 查询测评模板列表
export function listTemplate(query) {
  return request({
    url: '/bid/template/list',
    method: 'get',
    params: query
  })
}

// 查询测评模板详细
export function getTemplate(id) {
  return request({
    url: '/bid/template/' + id,
    method: 'get'
  })
}

// 新增测评模板
export function addTemplate(data) {
  return request({
    url: '/bid/template',
    method: 'post',
    data: data
  })
}

// 修改测评模板
export function updateTemplate(data) {
  return request({
    url: '/bid/template',
    method: 'put',
    data: data
  })
}

// 删除测评模板
export function delTemplate(id) {
  return request({
    url: '/bid/template/' + id,
    method: 'delete'
  })
}

// 导出测评模板
export function exportTemplate(query) {
  return request({
    url: '/bid/template/export',
    method: 'get',
    params: query
  })
}