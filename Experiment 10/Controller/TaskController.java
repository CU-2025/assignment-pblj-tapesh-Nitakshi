package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//Doing job of intercepting the request
@Controller
public class TaskController {
    private final TaskService taskService;
    //constructor injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    //mapping to fetch all tasks
    @GetMapping
    public String getTasks(Model model){
         List<Task> tasks = taskService.getAllTasks();
         model.addAttribute("tasks",tasks);
         return "tasks"; //name of thy template
    }
    @PostMapping
    public String createTasks(@RequestParam String title){
        taskService.createTasks(title);
        return "redirect:/"; //redirect to root url of application
    }
    @GetMapping("/{id}/delete") //delete the task
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/"; 
    }
    @GetMapping("/{id}/toggle") //delete the task
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }

}
