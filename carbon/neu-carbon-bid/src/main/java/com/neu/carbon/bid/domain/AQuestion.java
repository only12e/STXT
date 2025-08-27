package com.neu.carbon.bid.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 题目对象 a_question
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@ApiModel("题目")
public class AQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @ApiModelProperty("序号")
    private Long id;

    /** 模板id */
    @ApiModelProperty("模板id")
    @Excel(name = "模板id")
    private Long templateId;

    /** 题干 */
    @ApiModelProperty("题干")
    @Excel(name = "题干")
    private String name;

    /** 题型(1单选 2多选 3判断） */
    @ApiModelProperty("题型(1单选 2多选 3判断）")
    @Excel(name = "题型(1单选 2多选 3判断）")
    private String type;

    /** 分数 */
    @ApiModelProperty("分数")
    @Excel(name = "分数")
    private Long score;

    /** 题目选项信息 */
	@ApiModelProperty(hidden = true)
    private List<AOptions> aOptionsList;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    public List<AOptions> getaOptionsList() {
        return aOptionsList;
    }

    public void setaOptionsList(List<AOptions> aOptionsList) {
        this.aOptionsList = aOptionsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("name", getName())
            .append("type", getType())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("aOptionsList", getaOptionsList())
            .toString();
    }
}
