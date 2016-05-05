package com.kaishengit.mapper;

import com.kaishengit.pojo.Task;

import java.util.List;

public interface TaskMapper {

    /**
     * 保存新的待办任务
     * @param task
     */
    void save(Task task);

    /**
     * 根据userid查询对应的待办任务
     * @param currentUserId
     * @return
     */
    List<Task> findByUserId(Integer currentUserId);

    /**
     * 根据主键查找
     * @param taskId
     * @return
     */
    Task findById(String taskId);

    /**
     * 修改
     * @param task
     */
    void update(Task task);
}
