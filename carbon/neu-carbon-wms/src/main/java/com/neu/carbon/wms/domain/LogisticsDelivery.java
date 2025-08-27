package com.neu.carbon.wms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物流发货对象 logistics_delivery
 *
 * @author neuedu
 * @date 2024-12-06
 */
@ApiModel("物流发货")
public class LogisticsDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;

    /** 订单号 */
    @ApiModelProperty("订单号")
    @Excel(name = "订单号")
    private String orderNo;

    /** 销售发货id */
    @ApiModelProperty("销售发货id")
    @Excel(name = "销售发货id")
    private Long deliveryId;
    private String deliveryNo;

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    /** 发货人 */
    @ApiModelProperty("发货人")
    @Excel(name = "发货人")
    private String deliveryUser;

    /** 发货人联系电话 */
    @ApiModelProperty("发货人联系电话")
    @Excel(name = "发货人联系电话")
    private String deliveryUserContact;

    /** 发货地址 */
    @ApiModelProperty("发货地址")
    @Excel(name = "发货地址")
    private String deliveryAdress;

    /** 收货人 */
    @ApiModelProperty("收货人")
    @Excel(name = "收货人")
    private String receiveUser;

    /** 收货人联系电话 */
    @ApiModelProperty("收货人联系电话")
    @Excel(name = "收货人联系电话")
    private String receiveUserContact;

    /** 收货地址 */
    @ApiModelProperty("收货地址")
    @Excel(name = "收货地址")
    private String receiveAdress;

    /** 物流途径地 */
    @ApiModelProperty("物流途径地")
    @Excel(name = "物流途径地")
    private String wuliuRoute;

    /** 发货状态 */
    @ApiModelProperty("发货状态")
    @Excel(name = "发货状态")
    private String deliveryStatus;

    /** 物流地图 */
    @ApiModelProperty("物流地图")
    @Excel(name = "物流地图")
    private String wuliuMap;

    /** 备用字段1 */
    @ApiModelProperty("备用字段1")
    @Excel(name = "备用字段1")
    private String remark1;

    /** 备用字段2 */
    @ApiModelProperty("备用字段2")
    @Excel(name = "备用字段2")
    private String remark2;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }
    public void setDeliveryId(Long deliveryId)
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId()
    {
        return deliveryId;
    }
    public void setDeliveryUser(String deliveryUser)
    {
        this.deliveryUser = deliveryUser;
    }

    public String getDeliveryUser()
    {
        return deliveryUser;
    }
    public void setDeliveryUserContact(String deliveryUserContact)
    {
        this.deliveryUserContact = deliveryUserContact;
    }

    public String getDeliveryUserContact()
    {
        return deliveryUserContact;
    }
    public void setDeliveryAdress(String deliveryAdress)
    {
        this.deliveryAdress = deliveryAdress;
    }

    public String getDeliveryAdress()
    {
        return deliveryAdress;
    }
    public void setReceiveUser(String receiveUser)
    {
        this.receiveUser = receiveUser;
    }

    public String getReceiveUser()
    {
        return receiveUser;
    }
    public void setReceiveUserContact(String receiveUserContact)
    {
        this.receiveUserContact = receiveUserContact;
    }

    public String getReceiveUserContact()
    {
        return receiveUserContact;
    }
    public void setReceiveAdress(String receiveAdress)
    {
        this.receiveAdress = receiveAdress;
    }

    public String getReceiveAdress()
    {
        return receiveAdress;
    }
    public void setWuliuRoute(String wuliuRoute)
    {
        this.wuliuRoute = wuliuRoute;
    }

    public String getWuliuRoute()
    {
        return wuliuRoute;
    }
    public void setDeliveryStatus(String deliveryStatus)
    {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus()
    {
        return deliveryStatus;
    }
    public void setWuliuMap(String wuliuMap)
    {
        this.wuliuMap = wuliuMap;
    }

    public String getWuliuMap()
    {
        return wuliuMap;
    }
    public void setRemark1(String remark1)
    {
        this.remark1 = remark1;
    }

    public String getRemark1()
    {
        return remark1;
    }
    public void setRemark2(String remark2)
    {
        this.remark2 = remark2;
    }

    public String getRemark2()
    {
        return remark2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("deliveryId", getDeliveryId())
                .append("deliveryUser", getDeliveryUser())
                .append("deliveryUserContact", getDeliveryUserContact())
                .append("deliveryAdress", getDeliveryAdress())
                .append("receiveUser", getReceiveUser())
                .append("receiveUserContact", getReceiveUserContact())
                .append("receiveAdress", getReceiveAdress())
                .append("wuliuRoute", getWuliuRoute())
                .append("deliveryStatus", getDeliveryStatus())
                .append("wuliuMap", getWuliuMap())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("remark", getRemark())
                .append("remark1", getRemark1())
                .append("remark2", getRemark2())
                .toString();
    }
}
