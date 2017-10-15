package com.todo.service;

import java.util.Map;

import com.todo.bean.TodoTransaction;


public interface ITodoService {

	TodoTransaction getTodo(Long id);

	TodoTransaction saveOrUpdateTodo(TodoTransaction todoRequest);

	Map<String, Object> getTodoList(String status, Integer limit, Integer offset,
			String sortBy, String sortType, String query);


}
