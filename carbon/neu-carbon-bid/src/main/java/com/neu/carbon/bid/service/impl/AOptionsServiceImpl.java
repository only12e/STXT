package com.neu.carbon.bid.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.AOptionsMapper;
import com.neu.carbon.bid.domain.AOptions;
import com.neu.carbon.bid.service.IAOptionsService;

/**
 * 题目选项Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Service
public class AOptionsServiceImpl implements IAOptionsService 
{
    @Autowired
    private AOptionsMapper aOptionsMapper;

    /**
     * 查询题目选项
     * 
     * @param id 题目选项ID
     * @return 题目选项
     */
    @Override
    public AOptions selectAOptionsById(Long id)
    {
        return aOptionsMapper.selectAOptionsById(id);
    }

    /**
     * 查询题目选项列表
     * 
     * @param aOptions 题目选项
     * @return 题目选项
     */
    @Override
    public List<AOptions> selectAOptionsList(AOptions aOptions)
    {
        return aOptionsMapper.selectAOptionsList(aOptions);
    }

    /**
     * 新增题目选项
     * 
     * @param aOptions 题目选项
     * @return 结果
     */
    @Override
    public int insertAOptions(AOptions aOptions)
    {
        aOptions.setCreateTime(DateUtils.getNowDate());
        return aOptionsMapper.insertAOptions(aOptions);
    }

    /**
     * 修改题目选项
     * 
     * @param aOptions 题目选项
     * @return 结果
     */
    @Override
    public int updateAOptions(AOptions aOptions)
    {
        aOptions.setUpdateTime(DateUtils.getNowDate());
        return aOptionsMapper.updateAOptions(aOptions);
    }

    /**
     * 批量删除题目选项
     * 
     * @param ids 需要删除的题目选项ID
     * @return 结果
     */
    @Override
    public int deleteAOptionsByIds(Long[] ids)
    {
        return aOptionsMapper.deleteAOptionsByIds(ids);
    }

    /**
     * 删除题目选项信息
     * 
     * @param id 题目选项ID
     * @return 结果
     */
    @Override
    public int deleteAOptionsById(Long id)
    {
        return aOptionsMapper.deleteAOptionsById(id);
    }
}
