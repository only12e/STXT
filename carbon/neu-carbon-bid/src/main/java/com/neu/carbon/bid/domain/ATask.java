package com.neu.carbon.bid.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 任务发布对象 a_task
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@ApiModel("任务发布")
public class ATask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @ApiModelProperty("序号")
    private Long id;

    /** 模板id */
    @ApiModelProperty("模板id")
    @Excel(name = "模板id")
    private Long templateId;
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /** 测评任务名称 */
    @ApiModelProperty("测评任务名称")
    @Excel(name = "测评任务名称")
    private String name;

    /** 测评周期 */
    @ApiModelProperty("测评周期")
    @Excel(name = "测评周期")
    private String testDate;

    /** 答题时长 */
    @ApiModelProperty("答题时长")
    @Excel(name = "答题时长")
    private String replyTime;

    /** 状态（0 未发布 1已发布） */
    @ApiModelProperty("状态")
    @Excel(name = "状态", readConverterExp = "0=,未=发布,1=已发布")
    private String status;

    /** 问题数量 */
    @ApiModelProperty("问题数量")
    @Excel(name = "问题数量")
    private Long questionNum;

    /** 发布时间 */
    @ApiModelProperty(value="发布时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTestDate(String testDate) 
    {
        this.testDate = testDate;
    }

    public String getTestDate() 
    {
        return testDate;
    }
    public void setReplyTime(String replyTime) 
    {
        this.replyTime = replyTime;
    }

    public String getReplyTime() 
    {
        return replyTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setQuestionNum(Long questionNum) 
    {
        this.questionNum = questionNum;
    }

    public Long getQuestionNum() 
    {
        return questionNum;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("name", getName())
            .append("testDate", getTestDate())
            .append("replyTime", getReplyTime())
            .append("status", getStatus())
            .append("questionNum", getQuestionNum())
            .append("publishTime", getPublishTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
