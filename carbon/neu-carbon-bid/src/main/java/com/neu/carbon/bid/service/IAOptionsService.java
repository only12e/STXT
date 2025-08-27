package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.AOptions;

/**
 * 题目选项Service接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface IAOptionsService 
{
    /**
     * 查询题目选项
     * 
     * @param id 题目选项ID
     * @return 题目选项
     */
    public AOptions selectAOptionsById(Long id);

    /**
     * 查询题目选项列表
     * 
     * @param aOptions 题目选项
     * @return 题目选项集合
     */
    public List<AOptions> selectAOptionsList(AOptions aOptions);

    /**
     * 新增题目选项
     * 
     * @param aOptions 题目选项
     * @return 结果
     */
    public int insertAOptions(AOptions aOptions);

    /**
     * 修改题目选项
     * 
     * @param aOptions 题目选项
     * @return 结果
     */
    public int updateAOptions(AOptions aOptions);

    /**
     * 批量删除题目选项
     * 
     * @param ids 需要删除的题目选项ID
     * @return 结果
     */
    public int deleteAOptionsByIds(Long[] ids);

    /**
     * 删除题目选项信息
     * 
     * @param id 题目选项ID
     * @return 结果
     */
    public int deleteAOptionsById(Long id);
}
