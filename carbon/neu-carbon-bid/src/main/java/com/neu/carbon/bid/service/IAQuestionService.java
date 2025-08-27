package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.AQuestion;

/**
 * 题目Service接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface IAQuestionService 
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
     * 批量删除题目
     * 
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    public int deleteAQuestionByIds(Long[] ids);

    /**
     * 删除题目信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    public int deleteAQuestionById(Long id);
}
