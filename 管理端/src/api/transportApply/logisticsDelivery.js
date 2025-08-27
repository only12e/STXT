import request from '@/utils/request'

// 查询物流发货列表
export function listLogisticsDelivery(query) {
  return request({
    url: '/transportApply/logisticsDelivery/list',
    method: 'get',
    params: query
  })
}

// 查询物流发货详细
export function getLogisticsDelivery(id) {
  return request({
    url: '/transportApply/logisticsDelivery/' + id,
    method: 'get'
  })
}

// 新增物流发货
export function addLogisticsDelivery(data) {
  return request({
    url: '/transportApply/logisticsDelivery',
    method: 'post',
    data: data
  })
}

// 修改物流发货
export function updateLogisticsDelivery(data) {
  return request({
    url: '/transportApply/logisticsDelivery',
    method: 'put',
    data: data
  })
}

// 删除物流发货
export function delLogisticsDelivery(id) {
  return request({
    url: '/transportApply/logisticsDelivery/' + id,
    method: 'delete'
  })
}

// 导出物流发货
export function exportLogisticsDelivery(query) {
  return request({
    url: '/transportApply/logisticsDelivery/export',
    method: 'get',
    params: query
  })
}