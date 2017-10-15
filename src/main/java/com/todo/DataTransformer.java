package com.todo;

import java.util.List;
import java.util.Map;

import com.Util.Util;
import com.constants.TodoConstants;
import com.todo.bean.TodoTransaction;
import com.todo.to.response.TodoListResponse;
/**
 * 
 * @author rajesh
 *
 */
public class DataTransformer {
	
	public static TodoTransaction setDeleteTransactionDetails(Long id){
	    	
	    	TodoTransaction deleteTansaction = new TodoTransaction();
	    	if(!Util.isNull(id)){
		    	deleteTansaction.setIsDeleted(true);
		    	deleteTansaction.setId(id);
	    	}else{
	    		Util.throwException(TodoConstants.ID_CANT_BE_NULL);
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
	
	
	public static TodoTransaction setMarkAsCompleted(Long id){
    	
    	TodoTransaction markCompleted = new TodoTransaction();
    	if(!Util.isNull(id)){
    		markCompleted.setStatusId(2L);
    		markCompleted.setId(id);
    	}else{
    		Util.throwException(TodoConstants.ID_CANT_BE_NULL);
    	}
    	return markCompleted;

    }
}
