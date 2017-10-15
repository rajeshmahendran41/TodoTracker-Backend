package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constants.CommonConstants;
import com.constants.TodoConstants;
import com.todo.DataTransformer;
import com.todo.bean.TodoTransaction;
import com.todo.service.ITodoService;
import com.todo.to.request.TodoRequest;
import com.todo.to.response.TodoListResponse;
import com.todo.to.response.TodoResponse;
/**
 * 
 * @author rajesh
 *
 */

@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	private ITodoService iTodoService;
	
    @RequestMapping(path="/todo/{id}",method = RequestMethod.GET)
    public TodoResponse getTodo(@PathVariable("id")Long id){
        
    	TodoResponse res = new TodoResponse();
		res.setTodoRespone(iTodoService.getTodo(id));
		res.setMessage(TodoConstants.TODO_FETCH_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    @RequestMapping(path="/todo",method = RequestMethod.POST)
    public TodoResponse saveTodo(@RequestBody TodoRequest todoRequest){
        
    	TodoResponse res = new TodoResponse();
		res.setTodoRespone(iTodoService.saveOrUpdateTodo(todoRequest.getTodoDetails()));
		res.setMessage(TodoConstants.TODO_ADDED_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    @RequestMapping(path="/todo",method = RequestMethod.PUT)
    public TodoResponse updateTodo(@RequestBody TodoRequest todoRequest){
        
    	TodoResponse res = new TodoResponse();
		res.setTodoRespone(iTodoService.saveOrUpdateTodo(todoRequest.getTodoDetails()));
		res.setMessage(TodoConstants.TODO_UPDATED_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    @RequestMapping(path="/todo/{id}",method = RequestMethod.DELETE)
    public TodoResponse deleteTodo(@PathVariable("id")Long id){
        
    	TodoResponse res = new TodoResponse();
    	TodoTransaction deleteTansaction = DataTransformer.setDeleteTransactionDetails(id);
		res.setTodoRespone(iTodoService.saveOrUpdateTodo(deleteTansaction));
		res.setMessage(TodoConstants.TODO_DELETED_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    @RequestMapping(path="/todo/list",method = RequestMethod.GET)
    public TodoListResponse getTodos(@RequestParam(required = false, defaultValue = "All") String status,
    		@RequestParam(required = false) Integer limit,
    		@RequestParam(required = false) Integer offset,
    		@RequestParam(required = false,defaultValue = "createdAt") String sortBy,
    		@RequestParam(required = false,defaultValue = "Desc") String sortType,
    		@RequestParam(required = false) String query){
        
    	TodoListResponse res = new TodoListResponse();
		res = DataTransformer.transformTodoListDetails(iTodoService.getTodoList(status,limit,offset,sortBy,sortType,query));
		res.setMessage(TodoConstants.TODO_FETCH_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    @RequestMapping(path="/todo/{id}/mark",method = RequestMethod.PUT)
    public TodoResponse markCompleted(@PathVariable("id")Long id){
        
    	TodoResponse res = new TodoResponse();
    	TodoTransaction markCompleted = DataTransformer.setMarkAsCompleted(id);
		res.setTodoRespone(iTodoService.saveOrUpdateTodo(markCompleted));
		res.setMessage(TodoConstants.TODO_UPDATED_SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
    
    
	
}
