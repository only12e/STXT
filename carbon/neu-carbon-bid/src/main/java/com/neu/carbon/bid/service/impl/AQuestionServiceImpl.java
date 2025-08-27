package com.neu.carbon.bid.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.neu.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.neu.carbon.bid.domain.AOptions;
import com.neu.carbon.bid.mapper.AQuestionMapper;
import com.neu.carbon.bid.domain.AQuestion;
import com.neu.carbon.bid.service.IAQuestionService;

/**
 * 题目Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Service
public class AQuestionServiceImpl implements IAQuestionService 
{
    @Autowired
    private AQuestionMapper aQuestionMapper;

    /**
     * 查询题目
     * 
     * @param id 题目ID
     * @return 题目
     */
    @Override
    public AQuestion selectAQuestionById(Long id)
    {
        return aQuestionMapper.selectAQuestionById(id);
    }

    /**
     * 查询题目列表
     * 
     * @param aQuestion 题目
     * @return 题目
     */
    @Override
    public List<AQuestion> selectAQuestionList(AQuestion aQuestion)
    {
        return aQuestionMapper.selectAQuestionList(aQuestion);
    }

    /**
     * 新增题目
     * 
     * @param aQuestion 题目
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAQuestion(AQuestion aQuestion)
    {
        aQuestion.setCreateTime(DateUtils.getNowDate());
        int rows = aQuestionMapper.insertAQuestion(aQuestion);
        insertAOptions(aQuestion);
        return rows;
    }

    /**
     * 修改题目
     * 
     * @param aQuestion 题目
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAQuestion(AQuestion aQuestion)
    {
        aQuestion.setUpdateTime(DateUtils.getNowDate());
        aQuestionMapper.deleteAOptionsByQuestionId(aQuestion.getId());
        insertAOptions(aQuestion);
        return aQuestionMapper.updateAQuestion(aQuestion);
    }

    /**
     * 批量删除题目
     * 
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAQuestionByIds(Long[] ids)
    {
        aQuestionMapper.deleteAOptionsByQuestionIds(ids);
        return aQuestionMapper.deleteAQuestionByIds(ids);
    }

    /**
     * 删除题目信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAQuestionById(Long id)
    {
        aQuestionMapper.deleteAOptionsByQuestionId(id);
        return aQuestionMapper.deleteAQuestionById(id);
    }

    /**
     * 新增题目选项信息
     * 
     * @param aQuestion 题目对象
     */
    public void insertAOptions(AQuestion aQuestion)
    {
        List<AOptions> aOptionsList = aQuestion.getaOptionsList();
        Long id = aQuestion.getId();
        if (StringUtils.isNotNull(aOptionsList))
        {
            List<AOptions> list = new ArrayList<AOptions>();
            for (AOptions aOptions : aOptionsList)
            {
                aOptions.setQuestionId(id);
                list.add(aOptions);
            }
            if (list.size() > 0)
            {
                aQuestionMapper.batchAOptions(list);
            }
        }
    }
}
