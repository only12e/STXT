package com.neu.carbon.bid.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.ATestTaskMapper;
import com.neu.carbon.bid.domain.ATestTask;
import com.neu.carbon.bid.service.IATestTaskService;

/**
 * 测评任务Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Service
public class ATestTaskServiceImpl implements IATestTaskService 
{
    @Autowired
    private ATestTaskMapper aTestTaskMapper;

    /**
     * 查询测评任务
     * 
     * @param id 测评任务ID
     * @return 测评任务
     */
    @Override
    public ATestTask selectATestTaskById(Long id)
    {
        return aTestTaskMapper.selectATestTaskById(id);
    }

    /**
     * 查询测评任务列表
     * 
     * @param aTestTask 测评任务
     * @return 测评任务
     */
    @Override
    public List<ATestTask> selectATestTaskList(ATestTask aTestTask)
    {
        return aTestTaskMapper.selectATestTaskList(aTestTask);
    }

    /**
     * 新增测评任务
     * 
     * @param aTestTask 测评任务
     * @return 结果
     */
    @Override
    public int insertATestTask(ATestTask aTestTask)
    {
        aTestTask.setCreateTime(DateUtils.getNowDate());
        return aTestTaskMapper.insertATestTask(aTestTask);
    }

    /**
     * 修改测评任务
     * 
     * @param aTestTask 测评任务
     * @return 结果
     */
    @Override
    public int updateATestTask(ATestTask aTestTask)
    {
        aTestTask.setUpdateTime(DateUtils.getNowDate());
        return aTestTaskMapper.updateATestTask(aTestTask);
    }

    /**
     * 批量删除测评任务
     * 
     * @param ids 需要删除的测评任务ID
     * @return 结果
     */
    @Override
    public int deleteATestTaskByIds(Long[] ids)
    {
        return aTestTaskMapper.deleteATestTaskByIds(ids);
    }

    /**
     * 删除测评任务信息
     * 
     * @param id 测评任务ID
     * @return 结果
     */
    @Override
    public int deleteATestTaskById(Long id)
    {
        return aTestTaskMapper.deleteATestTaskById(id);
    }
}
