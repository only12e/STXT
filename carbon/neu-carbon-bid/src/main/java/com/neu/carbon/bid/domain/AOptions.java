package com.neu.carbon.bid.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

/**
 * 题目选项对象 a_options
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public class AOptions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 题目id */
    @Excel(name = "题目id")
    private Long questionId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 是否正确(0错误 1正确） */
    @Excel(name = "是否正确(0错误 1正确）")
    private String isRight;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setIsRight(String isRight) 
    {
        this.isRight = isRight;
    }

    public String getIsRight() 
    {
        return isRight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("content", getContent())
            .append("isRight", getIsRight())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
