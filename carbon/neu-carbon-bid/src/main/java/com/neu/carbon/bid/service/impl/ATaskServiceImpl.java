package com.neu.carbon.bid.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.bid.mapper.ATaskMapper;
import com.neu.carbon.bid.domain.ATask;
import com.neu.carbon.bid.service.IATaskService;

/**
 * 任务发布Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@Service
public class ATaskServiceImpl implements IATaskService 
{
    @Autowired
    private ATaskMapper aTaskMapper;

    /**
     * 查询任务发布
     * 
     * @param id 任务发布ID
     * @return 任务发布
     */
    @Override
    public ATask selectATaskById(Long id)
    {
        return aTaskMapper.selectATaskById(id);
    }

    /**
     * 查询任务发布列表
     * 
     * @param aTask 任务发布
     * @return 任务发布
     */
    @Override
    public List<ATask> selectATaskList(ATask aTask)
    {
        return aTaskMapper.selectATaskList(aTask);
    }

    /**
     * 新增任务发布
     * 
     * @param aTask 任务发布
     * @return 结果
     */
    @Override
    public int insertATask(ATask aTask)
    {
        aTask.setCreateTime(DateUtils.getNowDate());
        return aTaskMapper.insertATask(aTask);
    }

    /**
     * 修改任务发布
     * 
     * @param aTask 任务发布
     * @return 结果
     */
    @Override
    public int updateATask(ATask aTask)
    {
        aTask.setUpdateTime(DateUtils.getNowDate());
        return aTaskMapper.updateATask(aTask);
    }

    /**
     * 批量删除任务发布
     * 
     * @param ids 需要删除的任务发布ID
     * @return 结果
     */
    @Override
    public int deleteATaskByIds(Long[] ids)
    {
        return aTaskMapper.deleteATaskByIds(ids);
    }

    /**
     * 删除任务发布信息
     * 
     * @param id 任务发布ID
     * @return 结果
     */
    @Override
    public int deleteATaskById(Long id)
    {
        return aTaskMapper.deleteATaskById(id);
    }
}
