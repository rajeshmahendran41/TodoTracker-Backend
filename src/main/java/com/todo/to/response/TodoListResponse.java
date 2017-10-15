package com.todo.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.todo.bean.TodoTransaction;

public class TodoListResponse  extends CommonResponse{
	
	private List<TodoTransaction> todoResponse;
	private Long listCount;

	public List<TodoTransaction> getTodoResponse() {
		return todoResponse;
	}

	public void setTodoResponse(List<TodoTransaction> todoResponse) {
		this.todoResponse = todoResponse;
	}

	public Long getListCount() {
		return listCount;
	}

	public void setListCount(Long listCount) {
		this.listCount = listCount;
	}

}
