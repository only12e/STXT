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
 * 测评任务对象 a_test_task
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@ApiModel("测评任务")
public class ATestTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @ApiModelProperty("序号")
    private Long id;

    /** 测评题目详情 */
    @ApiModelProperty("测评题目详情")
    @Excel(name = "测评题目详情")
    private String questionDetail;

    /** 测评周期 */
    @ApiModelProperty("测评周期")
    @Excel(name = "测评周期")
    private String testDate;

    /** 测评任务名称 */
    @ApiModelProperty("测评任务名称")
    @Excel(name = "测评任务名称")
    private String taskName;

    /** 安全等级（1低 2中 3高） */
    @ApiModelProperty("安全等级")
    @Excel(name = "安全等级", readConverterExp = "1=低,2=中,3=高")
    private String level;

    /** 得分 */
    @ApiModelProperty("得分")
    @Excel(name = "得分")
    private Long score;

    /** 总分 */
    @ApiModelProperty("总分")
    @Excel(name = "总分")
    private Long totalScore;

    /** 答题时长 */
    @ApiModelProperty("答题时长")
    @Excel(name = "答题时长")
    private String replyTime;

    /** 完成时间 */
    @ApiModelProperty(value="完成时间",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestionDetail(String questionDetail) 
    {
        this.questionDetail = questionDetail;
    }

    public String getQuestionDetail() 
    {
        return questionDetail;
    }
    public void setTestDate(String testDate) 
    {
        this.testDate = testDate;
    }

    public String getTestDate() 
    {
        return testDate;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }
    public void setReplyTime(String replyTime) 
    {
        this.replyTime = replyTime;
    }

    public String getReplyTime() 
    {
        return replyTime;
    }
    public void setFinishTime(Date finishTime) 
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() 
    {
        return finishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionDetail", getQuestionDetail())
            .append("testDate", getTestDate())
            .append("taskName", getTaskName())
            .append("level", getLevel())
            .append("score", getScore())
            .append("totalScore", getTotalScore())
            .append("replyTime", getReplyTime())
            .append("finishTime", getFinishTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
