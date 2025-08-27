package com.neu.carbon.bid.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.neu.carbon.bid.domain.AOptions;
import com.neu.carbon.bid.domain.AnswerDto;
import com.neu.carbon.bid.service.IAOptionsService;
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
import com.neu.carbon.bid.domain.AQuestion;
import com.neu.carbon.bid.service.IAQuestionService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 题目Controller
 *
 * @author neuedu
 * @date 2024-05-19
 */
@Api(tags = {"题目"})
@RestController
@RequestMapping("/bid/question")
public class AQuestionController extends BaseController {
    @Autowired
    private IAQuestionService aQuestionService;
    @Autowired
    private IAOptionsService optionsService;


    @PostMapping("/check")
    public AjaxResult check(@RequestBody List<AnswerDto> answerDtos) {
        List<String> result = new ArrayList<>();
        long rightCount = answerDtos.stream()
                .filter(r -> {
                    AQuestion aQuestion = aQuestionService.selectAQuestionById(r.getQuestionId());
                    AOptions aOptions = new AOptions();
                    aOptions.setQuestionId(r.getQuestionId());
                    aOptions.setIsRight("1");
                    List<AOptions> aOptionsList = optionsService.selectAOptionsList(aOptions);
                    List<Long> rightIds = new ArrayList<>();
                    for (AOptions options : aOptionsList) {
                        rightIds.add(options.getId());
                    }
                    List<Long> optionIds = r.getOptionIds();
                    if (optionIds == null || optionIds.isEmpty()) {
                        result.add(aQuestion.getName() + "错误");
                        return false;
                    }
                    if (optionIds.size() != rightIds.size()) {
                        result.add(aQuestion.getName() + "错误");
                        return false;
                    }
                    for (Long optionId : optionIds) {
                        if (!rightIds.contains(optionId)) {
                            result.add(aQuestion.getName() + "错误");
                            return false;
                        }
                    }
                    result.add(aQuestion.getName() + "正确");
                    return true;
                }).count();
        Map<String, Object> map = new HashMap<>();
        map.put("score", rightCount * 10);
        map.put("result", result);
        return AjaxResult.success(map);
    }


    /**
     * 查询题目列表
     */
    @GetMapping("/list")
    @ApiOperation("查询题目列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = AQuestion.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(AQuestion aQuestion) {
        startPage();
        List<AQuestion> list = aQuestionService.selectAQuestionList(aQuestion);
        return getDataTable(list);
    }

    @GetMapping("/questionDetail/list")
    @ApiOperation("查询题目列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = AQuestion.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo questionDetailList(AQuestion aQuestion) {
        startPage();
        List<AQuestion> list = aQuestionService.selectAQuestionList(aQuestion);
        List<Map> mapList = new ArrayList<>();
        for (AQuestion question : list) {
            HashMap map = new HashMap<>();
            AOptions aOptions = new AOptions();
            aOptions.setQuestionId(question.getId());
            List<AOptions> aOptions1 = optionsService.selectAOptionsList(aOptions);
            map.put("id", question.getId());
            map.put("type", question.getType());
            map.put("question", question.getName());
            map.put("options", aOptions1);
            mapList.add(map);
        }
        return getDataTable(mapList);
    }

    /**
     * 导出题目列表
     */
    @ApiOperation("导出题目列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('bid:question:export')")
    @Log(title = "题目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AQuestion aQuestion) {
        List<AQuestion> list = aQuestionService.selectAQuestionList(aQuestion);
        ExcelUtil<AQuestion> util = new ExcelUtil<AQuestion>(AQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 获取题目详细信息
     */
    @ApiOperation("获取题目详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = AQuestion.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(aQuestionService.selectAQuestionById(id));
    }

    /**
     * 新增题目
     */
    @ApiOperation("新增题目")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:question:add')")
    @Log(title = "题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AQuestion aQuestion) {
        return toAjax(aQuestionService.insertAQuestion(aQuestion));
    }

    /**
     * 修改题目
     */
    @ApiOperation("修改题目")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:question:edit')")
    @Log(title = "题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AQuestion aQuestion) {
        return toAjax(aQuestionService.updateAQuestion(aQuestion));
    }

    /**
     * 删除题目
     */
    @ApiOperation("删除题目")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('bid:question:remove')")
    @Log(title = "题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(aQuestionService.deleteAQuestionByIds(ids));
    }
}
