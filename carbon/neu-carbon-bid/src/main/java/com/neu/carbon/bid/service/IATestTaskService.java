package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.ATestTask;

/**
 * 测评任务Service接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface IATestTaskService 
{
    /**
     * 查询测评任务
     * 
     * @param id 测评任务ID
     * @return 测评任务
     */
    public ATestTask selectATestTaskById(Long id);

    /**
     * 查询测评任务列表
     * 
     * @param aTestTask 测评任务
     * @return 测评任务集合
     */
    public List<ATestTask> selectATestTaskList(ATestTask aTestTask);

    /**
     * 新增测评任务
     * 
     * @param aTestTask 测评任务
     * @return 结果
     */
    public int insertATestTask(ATestTask aTestTask);

    /**
     * 修改测评任务
     * 
     * @param aTestTask 测评任务
     * @return 结果
     */
    public int updateATestTask(ATestTask aTestTask);

    /**
     * 批量删除测评任务
     * 
     * @param ids 需要删除的测评任务ID
     * @return 结果
     */
    public int deleteATestTaskByIds(Long[] ids);

    /**
     * 删除测评任务信息
     * 
     * @param id 测评任务ID
     * @return 结果
     */
    public int deleteATestTaskById(Long id);
}
