package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.TaskModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskCntroller {
    ArrayList<TaskModel> tasks=new ArrayList<>();


    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody TaskModel task) {
        tasks.add(task);
        return new ApiResponse("Added success");
    }

    @GetMapping("/get")
    public ArrayList<TaskModel> getTask() {
        return tasks;
    }
    @PutMapping("/update/{index}")
    public ApiResponse setTasks(@PathVariable int index, @RequestBody TaskModel task) {
        tasks.set(index, task) ;
        return new ApiResponse("changes success");
    }
    @DeleteMapping("/delete/{index}")
    public String deleteTask(@PathVariable int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return "Task deleted successfully";
        }
        return "index out of bounds";
    }

    @GetMapping("/search")
    public List<TaskModel> searchTask(@RequestParam String title) {
        List<TaskModel> foundTasks = new ArrayList<>();
        for (TaskModel task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    @PutMapping("/update")
    public <status> ArrayList<TaskModel> setStatus(@RequestBody boolean status) {
        if (status==true){
            return setStatus(false);
        }
        return tasks;
    }
    @PutMapping ("/status2/{index}")
    public String changeStatus(@PathVariable int index, @RequestParam boolean status) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus(status);
            return "Status updated successfully";
        }
        return "index out of bounds";
    }

}
