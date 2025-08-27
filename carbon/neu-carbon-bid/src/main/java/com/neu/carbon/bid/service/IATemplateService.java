package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.ATemplate;

/**
 * 测评模板Service接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface IATemplateService 
{
    /**
     * 查询测评模板
     * 
     * @param id 测评模板ID
     * @return 测评模板
     */
    public ATemplate selectATemplateById(Long id);

    /**
     * 查询测评模板列表
     * 
     * @param aTemplate 测评模板
     * @return 测评模板集合
     */
    public List<ATemplate> selectATemplateList(ATemplate aTemplate);

    /**
     * 新增测评模板
     * 
     * @param aTemplate 测评模板
     * @return 结果
     */
    public int insertATemplate(ATemplate aTemplate);

    /**
     * 修改测评模板
     * 
     * @param aTemplate 测评模板
     * @return 结果
     */
    public int updateATemplate(ATemplate aTemplate);

    /**
     * 批量删除测评模板
     * 
     * @param ids 需要删除的测评模板ID
     * @return 结果
     */
    public int deleteATemplateByIds(Long[] ids);

    /**
     * 删除测评模板信息
     * 
     * @param id 测评模板ID
     * @return 结果
     */
    public int deleteATemplateById(Long id);
}
