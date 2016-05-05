package com.kaishengit.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kaishengit.dto.Message;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Task;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Inject
    private TaskService taskService;
    @Inject
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Customer> customerList = customerService.findCustomerByCurrentUser();
        List<Task> taskList = taskService.findAllByCurrentUser();

        List<Task> newTaskList = Lists.newArrayList(Collections2.filter(taskList, new Predicate<Task>() {
            @Override
            public boolean apply(Task task) {
                return !task.getDone();
            }
        }));

        List<Task> doneTaskList = Lists.newArrayList(Collections2.filter(taskList, new Predicate<Task>() {
            @Override
            public boolean apply(Task task) {
                return task.getDone();
            }
        }));

        model.addAttribute("newTaskList",newTaskList);
        model.addAttribute("doneTaskList",doneTaskList);
        model.addAttribute("customerList",customerList);
        return "task/list";
    }

    /**
     * 保存新的待办任务
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String newTask(Task task, RedirectAttributes redirectAttributes) {
        taskService.save(task);

        redirectAttributes.addFlashAttribute("message",new Message(Message.SUCCESS,"添加新任务成功"));
        return "redirect:/task";
    }

    /**
     * 改变任务的状态（已完成，未完成）
     */
    @RequestMapping(value = "/state/change",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeState(String taskId, boolean state) {
        Map<String,Object> result = Maps.newHashMap();

        try {
            taskService.changeTaskState(taskId, state);

            result.put("state","success");
        } catch (NotFoundException ex) {
            result.put("state","error");
            result.put("message","待办任务不存在");
        } catch (ForbiddenException ex) {
            result.put("state","error");
            result.put("message","权限不足");
        }
        return result;
    }

}
