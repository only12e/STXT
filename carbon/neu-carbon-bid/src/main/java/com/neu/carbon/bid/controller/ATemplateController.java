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
import com.neu.carbon.bid.domain.ATemplate;
import com.neu.carbon.bid.service.IATemplateService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 测评模板Controller
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Api(tags = {"测评模板"})
@RestController
@RequestMapping("/bid/template")
public class ATemplateController extends BaseController
{
    @Autowired
    private IATemplateService aTemplateService;

    /**
     * 查询测评模板列表
     */
    @GetMapping("/list")
    @ApiOperation("查询测评模板列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ATemplate.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ATemplate aTemplate)
    {
        startPage();
        List<ATemplate> list = aTemplateService.selectATemplateList(aTemplate);
        return getDataTable(list);
    }

    /**
     * 导出测评模板列表
     */
    @ApiOperation("导出测评模板列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:template:export')")
    @Log(title = "测评模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ATemplate aTemplate)
    {
        List<ATemplate> list = aTemplateService.selectATemplateList(aTemplate);
        ExcelUtil<ATemplate> util = new ExcelUtil<ATemplate>(ATemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 获取测评模板详细信息
     */
    @ApiOperation("获取测评模板详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ATemplate.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(aTemplateService.selectATemplateById(id));
    }

    /**
     * 新增测评模板
     */
    @ApiOperation("新增测评模板")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:template:add')")
    @Log(title = "测评模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ATemplate aTemplate)
    {
        return toAjax(aTemplateService.insertATemplate(aTemplate));
    }

    /**
     * 修改测评模板
     */
    @ApiOperation("修改测评模板")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:template:edit')")
    @Log(title = "测评模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ATemplate aTemplate)
    {
        return toAjax(aTemplateService.updateATemplate(aTemplate));
    }

    /**
     * 删除测评模板
     */
    @ApiOperation("删除测评模板")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:template:remove')")
    @Log(title = "测评模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aTemplateService.deleteATemplateByIds(ids));
    }
}
