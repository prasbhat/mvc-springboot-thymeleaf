package com.myzonesoft.todo.mvc.controller;

import com.myzonesoft.todo.mvc.model.Tasks;
import com.myzonesoft.todo.mvc.model.TodoTaskComments;
import com.myzonesoft.todo.mvc.util.TodoApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TodoViewController is the Controller class for the To-do Tracker Application.
 * This class is responsible for communication between the JSP pages and the other Controller.
 *
 */
@Controller
@SuppressWarnings("unused")
public class TodoViewController implements TodoApplicationConstants {

    //Variable declarations
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoViewController.class);
    private final String className = this.getClass().getSimpleName();

    @Autowired
    private TodoController todoController;

    /**
     * Method for redirecting to the index.jsp page
     * This method accepts HTTP_REQUEST_METHOD:GET
     * @param model required for ModelAndView
     *
     * @return Redirection to index.jsp using ModelAndView
     */
    @GetMapping
    public String gotoHome(ModelMap model) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        model.put("tasksItemList", todoController.getAllTasks().getBody());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return "index";
    }

    /**
     * Method for redirecting to the singleItemPage.jsp page
     * This method accepts HTTP_REQUEST_METHOD:GET
     * @param action can be view/edit
     * @param todoId Id of the To-do Task selected
     * @param model required for ModelAndView
     *
     * @return Redirection to singleViewPage.jsp using ModelAndView
     */
    @GetMapping("/singleItemView/{action}/{todoId}")
    public String goToSingleItemPage(ModelMap model, @PathVariable("action") String action,
                                           @PathVariable("todoId") Long todoId){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        Tasks tasksItem = Tasks.builder().title("").description("").dueDate(null).status("").build();
        if(todoId > 0) {
            tasksItem = todoController.getTasksById(todoId).getBody();
        }
        model.put("tasksItem", tasksItem);
        model.put("todoTaskComments", new TodoTaskComments());
        model.put("action",action);
        model.put("todoStatus",todoController.getTaskStatus().getBody());
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return "singleItemPage";
    }

    @PostMapping(value = "/createOrUpdate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendToCreate(Tasks tasksItem, TodoTaskComments todoTaskComments) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        LOGGER.debug("TodoObject="+ tasksItem);

        if (tasksItem.getSystemTasksId() != null && tasksItem.getSystemTasksId() > 0 ) {
            Set<TodoTaskComments> todoTaskCommentsSet = new HashSet<>();
            todoTaskCommentsSet.add(todoTaskComments);
            tasksItem.setTodoTaskCommentsSet(todoTaskCommentsSet);

            todoController.updateTaskItem(tasksItem);
        } else {
            Tasks response = todoController.createTodoTask(tasksItem).getBody();
        }

        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return "redirect:/";
    }

    @GetMapping("/deleteById/{id}")
    public String sendToDeleteById(@PathVariable long id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        LOGGER.info(MessageFormat.format(LOGGER_ENTRY, className, methodName));
        todoController.deleteTaskById(id);
        LOGGER.info(MessageFormat.format(LOGGER_EXIT, className, methodName));
        return "redirect:/";
    }
}
