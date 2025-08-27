package com.neu.carbon.bid.service;

import java.util.List;
import com.neu.carbon.bid.domain.ATask;

/**
 * 任务发布Service接口
 * 
 * @author neuedu
 * @date 2024-05-19
 */
public interface IATaskService 
{
    /**
     * 查询任务发布
     * 
     * @param id 任务发布ID
     * @return 任务发布
     */
    public ATask selectATaskById(Long id);

    /**
     * 查询任务发布列表
     * 
     * @param aTask 任务发布
     * @return 任务发布集合
     */
    public List<ATask> selectATaskList(ATask aTask);

    /**
     * 新增任务发布
     * 
     * @param aTask 任务发布
     * @return 结果
     */
    public int insertATask(ATask aTask);

    /**
     * 修改任务发布
     * 
     * @param aTask 任务发布
     * @return 结果
     */
    public int updateATask(ATask aTask);

    /**
     * 批量删除任务发布
     * 
     * @param ids 需要删除的任务发布ID
     * @return 结果
     */
    public int deleteATaskByIds(Long[] ids);

    /**
     * 删除任务发布信息
     * 
     * @param id 任务发布ID
     * @return 结果
     */
    public int deleteATaskById(Long id);
}
