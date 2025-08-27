import request from '@/utils/request'

// 查询题目列表
export function listQuestion(query) {
  return request({
    url: '/bid/question/list',
    method: 'get',
    params: query
  })
}
export function detailListQuestion(query) {
  return request({
    url: '/bid/question/questionDetail/list',
    method: 'get',
    params: query
  })
}

// 查询题目详细
export function getQuestion(id) {
  return request({
    url: '/bid/question/' + id,
    method: 'get'
  })
}

// 新增题目
export function addQuestion(data) {
  return request({
    url: '/bid/question',
    method: 'post',
    data: data
  })
}
// 新增题目
export function check(data) {
  return request({
    url: '/bid/question/check',
    method: 'post',
    data: data
  })
}

// 修改题目
export function updateQuestion(data) {
  return request({
    url: '/bid/question',
    method: 'put',
    data: data
  })
}

// 删除题目
export function delQuestion(id) {
  return request({
    url: '/bid/question/' + id,
    method: 'delete'
  })
}

// 导出题目
export function exportQuestion(query) {
  return request({
    url: '/bid/question/export',
    method: 'get',
    params: query
  })
}