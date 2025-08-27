package com.neu.carbon.wms.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.enums.BusinessType;
import com.neu.carbon.wms.domain.LogisticsDelivery;
import com.neu.carbon.wms.service.ILogisticsDeliveryService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 物流发货Controller
 *
 * @author neuedu
 * @date 2024-12-06
 */
@Api(tags = {"物流发货"})
@RestController
@RequestMapping("/transportApply/logisticsDelivery")
public class LogisticsDeliveryController extends BaseController
{
    @Autowired
    private ILogisticsDeliveryService logisticsDeliveryService;

    /**
     * 查询物流发货列表
     */
    @GetMapping("/list")
    @ApiOperation("查询物流发货列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = LogisticsDelivery.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(LogisticsDelivery logisticsDelivery)
    {
        startPage();
        List<LogisticsDelivery> list = logisticsDeliveryService.selectLogisticsDeliveryList(logisticsDelivery);
        return getDataTable(list);
    }

    /**
     * 导出物流发货列表
     */
    @ApiOperation("导出物流发货列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('transportApply:logisticsDelivery:export')")
    @Log(title = "物流发货", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LogisticsDelivery logisticsDelivery)
    {
        List<LogisticsDelivery> list = logisticsDeliveryService.selectLogisticsDeliveryList(logisticsDelivery);
        ExcelUtil<LogisticsDelivery> util = new ExcelUtil<LogisticsDelivery>(LogisticsDelivery.class);
        return util.exportExcel(list, "logisticsDelivery");
    }

    /**
     * 获取物流发货详细信息
     */
    @ApiOperation("获取物流发货详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = LogisticsDelivery.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(logisticsDeliveryService.selectLogisticsDeliveryById(id));
    }

    /**
     * 新增物流发货
     */
    @ApiOperation("新增物流发货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('transportApply:logisticsDelivery:add')")
    @Log(title = "物流发货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LogisticsDelivery logisticsDelivery)
    {
        return toAjax(logisticsDeliveryService.insertLogisticsDelivery(logisticsDelivery));
    }

    /**
     * 修改物流发货
     */
    @ApiOperation("修改物流发货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('transportApply:logisticsDelivery:edit')")
    @Log(title = "物流发货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LogisticsDelivery logisticsDelivery)
    {
        return toAjax(logisticsDeliveryService.updateLogisticsDelivery(logisticsDelivery));
    }

    /**
     * 删除物流发货
     */
    @ApiOperation("删除物流发货")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('transportApply:logisticsDelivery:remove')")
    @Log(title = "物流发货", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(logisticsDeliveryService.deleteLogisticsDeliveryByIds(ids));
    }
}
