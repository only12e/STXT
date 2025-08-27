package com.neu.carbon.bid.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.ATemplateMapper;
import com.neu.carbon.bid.domain.ATemplate;
import com.neu.carbon.bid.service.IATemplateService;

/**
 * 测评模板Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Service
public class ATemplateServiceImpl implements IATemplateService 
{
    @Autowired
    private ATemplateMapper aTemplateMapper;

    /**
     * 查询测评模板
     * 
     * @param id 测评模板ID
     * @return 测评模板
     */
    @Override
    public ATemplate selectATemplateById(Long id)
    {
        return aTemplateMapper.selectATemplateById(id);
    }

    /**
     * 查询测评模板列表
     * 
     * @param aTemplate 测评模板
     * @return 测评模板
     */
    @Override
    public List<ATemplate> selectATemplateList(ATemplate aTemplate)
    {
        return aTemplateMapper.selectATemplateList(aTemplate);
    }

    /**
     * 新增测评模板
     * 
     * @param aTemplate 测评模板
     * @return 结果
     */
    @Override
    public int insertATemplate(ATemplate aTemplate)
    {
        aTemplate.setCreateTime(DateUtils.getNowDate());
        return aTemplateMapper.insertATemplate(aTemplate);
    }

    /**
     * 修改测评模板
     * 
     * @param aTemplate 测评模板
     * @return 结果
     */
    @Override
    public int updateATemplate(ATemplate aTemplate)
    {
        aTemplate.setUpdateTime(DateUtils.getNowDate());
        return aTemplateMapper.updateATemplate(aTemplate);
    }

    /**
     * 批量删除测评模板
     * 
     * @param ids 需要删除的测评模板ID
     * @return 结果
     */
    @Override
    public int deleteATemplateByIds(Long[] ids)
    {
        return aTemplateMapper.deleteATemplateByIds(ids);
    }

    /**
     * 删除测评模板信息
     * 
     * @param id 测评模板ID
     * @return 结果
     */
    @Override
    public int deleteATemplateById(Long id)
    {
        return aTemplateMapper.deleteATemplateById(id);
    }
}
