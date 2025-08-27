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
import com.neu.carbon.bid.domain.ATestTask;
import com.neu.carbon.bid.service.IATestTaskService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 测评任务Controller
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Api(tags = {"测评任务"})
@RestController
@RequestMapping("/bid/testTask")
public class ATestTaskController extends BaseController
{
    @Autowired
    private IATestTaskService aTestTaskService;

    /**
     * 查询测评任务列表
     */
    @GetMapping("/list")
    @ApiOperation("查询测评任务列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ATestTask.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ATestTask aTestTask)
    {
        startPage();
        List<ATestTask> list = aTestTaskService.selectATestTaskList(aTestTask);
        return getDataTable(list);
    }

    /**
     * 导出测评任务列表
     */
    @ApiOperation("导出测评任务列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:testTask:export')")
    @Log(title = "测评任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ATestTask aTestTask)
    {
        List<ATestTask> list = aTestTaskService.selectATestTaskList(aTestTask);
        ExcelUtil<ATestTask> util = new ExcelUtil<ATestTask>(ATestTask.class);
        return util.exportExcel(list, "testTask");
    }

    /**
     * 获取测评任务详细信息
     */
    @ApiOperation("获取测评任务详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ATestTask.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(aTestTaskService.selectATestTaskById(id));
    }

    /**
     * 新增测评任务
     */
    @ApiOperation("新增测评任务")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
//    @PreAuthorize("@ss.hasPermi('bid:testTask:add')")
    @Log(title = "测评任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ATestTask aTestTask)
    {
        int i = aTestTaskService.insertATestTask(aTestTask);
        return AjaxResult.success(aTestTask.getId());
    }

    /**
     * 修改测评任务
     */
    @ApiOperation("修改测评任务")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:testTask:edit')")
    @Log(title = "测评任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ATestTask aTestTask)
    {
        return toAjax(aTestTaskService.updateATestTask(aTestTask));
    }

    /**
     * 删除测评任务
     */
    @ApiOperation("删除测评任务")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:testTask:remove')")
    @Log(title = "测评任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aTestTaskService.deleteATestTaskByIds(ids));
    }
}
