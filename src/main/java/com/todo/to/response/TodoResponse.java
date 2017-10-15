package com.todo.to.response;

import com.response.CommonResponse;
import com.todo.bean.TodoTransaction;

public class TodoResponse extends CommonResponse {

	
	private TodoTransaction todoRespone;

	public TodoTransaction getTodoRespone() {
		return todoRespone;
	}

	public void setTodoRespone(TodoTransaction todoRespone) {
		this.todoRespone = todoRespone;
	}
}
