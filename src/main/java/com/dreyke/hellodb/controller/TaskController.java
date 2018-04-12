package com.dreyke.hellodb.controller;

import com.dreyke.hellodb.model.Task;
import com.dreyke.hellodb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TaskController {

    /* TaskRepository is an object that interacts with Task database table */
    private final TaskRepository tasks;

    @Autowired
    public TaskController(TaskRepository tasks) {
        this.tasks = tasks;
    }

    /* Handles requests to the home page. Displays a form thats bound to a new Task Object
     * When the form is submitted from the browser, the Task object will be updated to
      * include the data entered in the form, and this Task object will be sent to the
      * server as part of the request */
    @RequestMapping("/")
    public ModelAndView addTask() {
        return new ModelAndView("createTask.html", "task", new Task());
    }

    /* Handles form submission requests. Saves the Task sent with the request, and
     * redirect the /allTasks route */
    @RequestMapping(value="/addTask", method = RequestMethod.POST)
    public RedirectView addNewTask(Task task) {
        tasks.save(task);
        return new RedirectView("/allTasks");
    }

    /* Handles requests to the /allTasks page. The modelMap object will be populated with a list
     * of Task objects that we'll get by asking the task repository to query the database.
      * Returns a View which will be combined with the modelMap data to produce the HTML page*/
    @RequestMapping("/allTasks")
    public ModelAndView allTasks(ModelMap modelMap){
        modelMap.addAttribute("tasks", tasks.findAllByOrderByUrgentDesc());
        return new ModelAndView("taskList.html", modelMap);
    }
}
