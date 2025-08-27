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
import com.neu.carbon.bid.domain.ATask;
import com.neu.carbon.bid.service.IATaskService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 任务发布Controller
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Api(tags = {"任务发布"})
@RestController
@RequestMapping("/bid/task")
public class ATaskController extends BaseController
{
    @Autowired
    private IATaskService aTaskService;

    /**
     * 查询任务发布列表
     */
    @GetMapping("/list")
    @ApiOperation("查询任务发布列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ATask.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ATask aTask)
    {
        startPage();
        List<ATask> list = aTaskService.selectATaskList(aTask);
        return getDataTable(list);
    }

    /**
     * 导出任务发布列表
     */
    @ApiOperation("导出任务发布列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:task:export')")
    @Log(title = "任务发布", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ATask aTask)
    {
        List<ATask> list = aTaskService.selectATaskList(aTask);
        ExcelUtil<ATask> util = new ExcelUtil<ATask>(ATask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取任务发布详细信息
     */
    @ApiOperation("获取任务发布详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ATask.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(aTaskService.selectATaskById(id));
    }

    /**
     * 新增任务发布
     */
    @ApiOperation("新增任务发布")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:task:add')")
    @Log(title = "任务发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ATask aTask)
    {
        return toAjax(aTaskService.insertATask(aTask));
    }

    /**
     * 修改任务发布
     */
    @ApiOperation("修改任务发布")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:task:edit')")
    @Log(title = "任务发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ATask aTask)
    {
        return toAjax(aTaskService.updateATask(aTask));
    }

    /**
     * 删除任务发布
     */
    @ApiOperation("删除任务发布")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:task:remove')")
    @Log(title = "任务发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aTaskService.deleteATaskByIds(ids));
    }
}
