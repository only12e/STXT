package com.neu.carbon.wms.service;

import java.util.List;
import com.neu.carbon.wms.domain.LogisticsDelivery;

/**
 * 物流发货Service接口
 *
 * @author neuedu
 * @date 2024-12-06
 */
public interface ILogisticsDeliveryService
{
    /**
     * 查询物流发货
     *
     * @param id 物流发货ID
     * @return 物流发货
     */
    public LogisticsDelivery selectLogisticsDeliveryById(Long id);

    /**
     * 查询物流发货列表
     *
     * @param logisticsDelivery 物流发货
     * @return 物流发货集合
     */
    public List<LogisticsDelivery> selectLogisticsDeliveryList(LogisticsDelivery logisticsDelivery);

    /**
     * 新增物流发货
     *
     * @param logisticsDelivery 物流发货
     * @return 结果
     */
    public int insertLogisticsDelivery(LogisticsDelivery logisticsDelivery);

    /**
     * 修改物流发货
     *
     * @param logisticsDelivery 物流发货
     * @return 结果
     */
    public int updateLogisticsDelivery(LogisticsDelivery logisticsDelivery);

    /**
     * 批量删除物流发货
     *
     * @param ids 需要删除的物流发货ID
     * @return 结果
     */
    public int deleteLogisticsDeliveryByIds(Long[] ids);

    /**
     * 删除物流发货信息
     *
     * @param id 物流发货ID
     * @return 结果
     */
    public int deleteLogisticsDeliveryById(Long id);
}
