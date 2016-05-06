package com.kaishengit.service;

import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.mapper.TaskMapper;
import com.kaishengit.pojo.Task;
import com.kaishengit.util.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class TaskService {

    @Inject
    private TaskMapper taskMapper;

    /**
     * 添加新的待办任务
     * @param task
     */
    public void save(Task task) {
        task.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        task.setUserid(ShiroUtil.getCurrentUserId());
        task.setDone(false);
        taskMapper.save(task);
    }

    /**
     * 获取当前登录用户的所有待办任务
     * @return
     */
    public List<Task> findAllByCurrentUser() {
        return taskMapper.findByUserId(ShiroUtil.getCurrentUserId());
    }

    /**
     * 改变任务的状态
     * @param taskId 任务ID
     * @param state 已完成 true,未完成 false
     */
    public void changeTaskState(String taskId, boolean state) {
        Task task = taskMapper.findById(taskId);
        if(task == null) {
            throw new NotFoundException();
        } else {
            if(task.getUserid().equals(ShiroUtil.getCurrentUserId())) {
                if(state) {
                    task.setDone(state);
                    task.setDonetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
                } else {
                    task.setDone(state);
                    task.setDonetime("");
                }
                taskMapper.update(task);
            } else {
                throw new ForbiddenException();
            }
        }
    }

    /**
     * 根据客户id查询相关未完成的待办任务
     * @param id
     * @return
     */
    public List<Task> findunDoneTaskByCustId(Integer id) {
        return taskMapper.findByCustIdAndState(id,false);
    }
}
