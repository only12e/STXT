package com.neu.carbon.bid.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 测评模板对象 a_template
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@ApiModel("测评模板")
public class ATemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @ApiModelProperty("序号")
    private Long id;

    /** 测评模板名称 */
    @ApiModelProperty("测评模板名称")
    @Excel(name = "测评模板名称")
    private String name;

    /** 安全等级 */
    @ApiModelProperty("安全等级")
    @Excel(name = "安全等级")
    private String level;

    /** 总分 */
    @ApiModelProperty("总分")
    @Excel(name = "总分")
    private Long totalScore;

    /** 状态 */
    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("level", getLevel())
            .append("totalScore", getTotalScore())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
