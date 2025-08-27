package com.neu.carbon.bid.controller;

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
import com.neu.carbon.bid.domain.AOptions;
import com.neu.carbon.bid.service.IAOptionsService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 题目选项Controller
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Api(tags = {"题目选项"})
@RestController
@RequestMapping("/bid/options")
public class AOptionsController extends BaseController
{
    @Autowired
    private IAOptionsService aOptionsService;

    /**
     * 查询题目选项列表
     */
    @GetMapping("/list")
    @ApiOperation("查询题目选项列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = AOptions.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(AOptions aOptions)
    {
        startPage();
        List<AOptions> list = aOptionsService.selectAOptionsList(aOptions);
        return getDataTable(list);
    }

    /**
     * 导出题目选项列表
     */
    @ApiOperation("导出题目选项列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:options:export')")
    @Log(title = "题目选项", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AOptions aOptions)
    {
        List<AOptions> list = aOptionsService.selectAOptionsList(aOptions);
        ExcelUtil<AOptions> util = new ExcelUtil<AOptions>(AOptions.class);
        return util.exportExcel(list, "options");
    }

    /**
     * 获取题目选项详细信息
     */
    @ApiOperation("获取题目选项详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = AOptions.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(aOptionsService.selectAOptionsById(id));
    }

    /**
     * 新增题目选项
     */
    @ApiOperation("新增题目选项")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:options:add')")
    @Log(title = "题目选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AOptions aOptions)
    {
        return toAjax(aOptionsService.insertAOptions(aOptions));
    }

    /**
     * 修改题目选项
     */
    @ApiOperation("修改题目选项")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:options:edit')")
    @Log(title = "题目选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AOptions aOptions)
    {
        return toAjax(aOptionsService.updateAOptions(aOptions));
    }

    /**
     * 删除题目选项
     */
    @ApiOperation("删除题目选项")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:options:remove')")
    @Log(title = "题目选项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aOptionsService.deleteAOptionsByIds(ids));
    }
}
