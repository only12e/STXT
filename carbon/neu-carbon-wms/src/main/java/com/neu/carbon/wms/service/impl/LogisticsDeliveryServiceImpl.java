package com.neu.carbon.wms.service.impl;

import java.util.List;
import java.util.Random;

import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.wms.mapper.LogisticsDeliveryMapper;
import com.neu.carbon.wms.domain.LogisticsDelivery;
import com.neu.carbon.wms.service.ILogisticsDeliveryService;

/**
 * 物流发货Service业务层处理
 *
 * @author neuedu
 * @date 2024-12-06
 */
@Service
public class LogisticsDeliveryServiceImpl implements ILogisticsDeliveryService
{
    @Autowired
    private LogisticsDeliveryMapper logisticsDeliveryMapper;

    /**
     * 查询物流发货
     *
     * @param id 物流发货ID
     * @return 物流发货
     */
    @Override
    public LogisticsDelivery selectLogisticsDeliveryById(Long id)
    {
        return logisticsDeliveryMapper.selectLogisticsDeliveryById(id);
    }

    /**
     * 查询物流发货列表
     *
     * @param logisticsDelivery 物流发货
     * @return 物流发货
     */
    @Override
    public List<LogisticsDelivery> selectLogisticsDeliveryList(LogisticsDelivery logisticsDelivery)
    {
        return logisticsDeliveryMapper.selectLogisticsDeliveryList(logisticsDelivery);
    }

    /**
     * 新增物流发货
     *
     * @param logisticsDelivery 物流发货
     * @return 结果
     */
    @Override
    public int insertLogisticsDelivery(LogisticsDelivery logisticsDelivery)
    {
        String orderNo = generateOrderId();
        logisticsDelivery.setOrderNo(orderNo);
        logisticsDelivery.setCreateTime(DateUtils.getNowDate());
        return logisticsDeliveryMapper.insertLogisticsDelivery(logisticsDelivery);
    }

    // 生成一个订单号（五个随机字母 + 五个随机数字）
    public static String generateOrderId() {
        Random random = new Random();
        StringBuilder orderId = new StringBuilder();

        // 生成五个随机字母
        for (int i = 0; i < 5; i++) {
            char letter = (char) ('A' + random.nextInt(26));  // 生成A-Z之间的字母
            orderId.append(letter);
        }

        // 生成五个随机数字
        for (int i = 0; i < 5; i++) {
            int digit = random.nextInt(10);  // 生成0-9之间的数字
            orderId.append(digit);
        }

        return orderId.toString();
    }
    /**
     * 修改物流发货
     *
     * @param logisticsDelivery 物流发货
     * @return 结果
     */
    @Override
    public int updateLogisticsDelivery(LogisticsDelivery logisticsDelivery)
    {
        logisticsDelivery.setUpdateTime(DateUtils.getNowDate());
        return logisticsDeliveryMapper.updateLogisticsDelivery(logisticsDelivery);
    }

    /**
     * 批量删除物流发货
     *
     * @param ids 需要删除的物流发货ID
     * @return 结果
     */
    @Override
    public int deleteLogisticsDeliveryByIds(Long[] ids)
    {
        return logisticsDeliveryMapper.deleteLogisticsDeliveryByIds(ids);
    }

    /**
     * 删除物流发货信息
     *
     * @param id 物流发货ID
     * @return 结果
     */
    @Override
    public int deleteLogisticsDeliveryById(Long id)
    {
        return logisticsDeliveryMapper.deleteLogisticsDeliveryById(id);
    }
}
