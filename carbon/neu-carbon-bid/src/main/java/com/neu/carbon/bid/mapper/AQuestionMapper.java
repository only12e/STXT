package com.neu.carbon.bid.mapper;

import java.util.List;
import com.neu.carbon.bid.domain.AQuestion;
import com.neu.carbon.bid.domain.AOptions;

/**
 * 题目Mapper接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface AQuestionMapper 
{
    /**
     * 查询题目
     * 
     * @param id 题目ID
     * @return 题目
     */
    public AQuestion selectAQuestionById(Long id);

    /**
     * 查询题目列表
     * 
     * @param aQuestion 题目
     * @return 题目集合
     */
    public List<AQuestion> selectAQuestionList(AQuestion aQuestion);

    /**
     * 新增题目
     * 
     * @param aQuestion 题目
     * @return 结果
     */
    public int insertAQuestion(AQuestion aQuestion);

    /**
     * 修改题目
     * 
     * @param aQuestion 题目
     * @return 结果
     */
    public int updateAQuestion(AQuestion aQuestion);

    /**
     * 删除题目
     * 
     * @param id 题目ID
     * @return 结果
     */
    public int deleteAQuestionById(Long id);

    /**
     * 批量删除题目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAQuestionByIds(Long[] ids);

    /**
     * 批量删除题目选项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAOptionsByQuestionIds(Long[] ids);
    
    /**
     * 批量新增题目选项
     * 
     * @param aOptionsList 题目选项列表
     * @return 结果
     */
    public int batchAOptions(List<AOptions> aOptionsList);
    

    /**
     * 通过题目ID删除题目选项信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteAOptionsByQuestionId(Long id);
}
