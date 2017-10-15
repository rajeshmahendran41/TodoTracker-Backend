package com.todo.dao;

import java.util.Map;

import org.hibernate.SessionFactory;

import com.todo.bean.TodoTransaction;

public interface ITodoDao {

	TodoTransaction getTodo(Long id, SessionFactory sessionFactory);

	TodoTransaction saveOrUpdateTodo(TodoTransaction todoRequest,
			SessionFactory sessionFactory);

	Map<String, Object> getTodoList(String status,
			Integer limit, Integer offset, String sortBy, String sortType,
			String query,SessionFactory sessionFactory);

}
