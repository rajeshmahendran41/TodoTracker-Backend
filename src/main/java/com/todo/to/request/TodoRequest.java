package com.todo.to.request;

import com.todo.bean.TodoTransaction;
/**
 * 
 * @author rajesh
 *
 */
public class TodoRequest {
	
	private TodoTransaction todoDetails;

	public TodoTransaction getTodoDetails() {
		return todoDetails;
	}

	public void setTodoDetails(TodoTransaction todoDetails) {
		this.todoDetails = todoDetails;
	}

}
