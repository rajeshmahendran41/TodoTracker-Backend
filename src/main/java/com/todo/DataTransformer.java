package com.todo;

import java.util.List;
import java.util.Map;

import com.Util.Util;
import com.todo.bean.TodoTransaction;
import com.todo.to.response.TodoListResponse;

public class DataTransformer {
	
	public static TodoTransaction setDeleteTransactionDetails(Long id){
	    	
	    	TodoTransaction deleteTansaction = new TodoTransaction();
	    	if(!Util.isNull(id)){
		    	deleteTansaction.setIsDeleted(true);
		    	deleteTansaction.setId(id);
	    	}else{
	    		Util.throwException("Id Field cant be null");
	    	}
	    	return deleteTansaction;
	
	    }
	
	@SuppressWarnings("unchecked")
	public static TodoListResponse transformTodoListDetails(Map<String,Object> todoMapResponse){
    	
		TodoListResponse todoListResponse = new TodoListResponse();
    	todoListResponse.setListCount((Long)todoMapResponse.get("count"));
    	todoListResponse.setTodoResponse((List<TodoTransaction>) todoMapResponse.get("list"));
    	return todoListResponse;


    }
}