package com.todo.service.impl;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.todo.bean.TodoTransaction;
import com.todo.dao.ITodoDao;
import com.todo.service.ITodoService;

@Service
public class TodoServiceImpl implements ITodoService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ITodoDao iTodoDao;

	@Override
	@Transactional
	public TodoTransaction getTodo(Long id) {
		
		if(!Util.isNull(id)){
			return iTodoDao.getTodo(id,sessionFactory);
		}else{
			
			Util.throwException("Id Can't Be null");
		}
		return null;
	}

	@Override
	@Transactional
	public TodoTransaction saveOrUpdateTodo(TodoTransaction todoRequest) {
		
		if(!Util.isNull(todoRequest.getId())){
			TodoTransaction existingTodoRequest = getTodo(todoRequest.getId());
			if(!Util.isNull(existingTodoRequest)){			
				todoRequest = updateTodoRequest(todoRequest,existingTodoRequest);
			}
		}else{
			todoRequest.setCreatedAt(Util.getCurrentTimeStamp());
			todoRequest.setUpdatedAt(Util.getCurrentTimeStamp());
			todoRequest.setStatusId(1L);
		}
		
		todoRequest = iTodoDao.saveOrUpdateTodo(todoRequest,sessionFactory);			
		return todoRequest;

	}
	
	
	private TodoTransaction updateTodoRequest(TodoTransaction todoRequest,
			TodoTransaction existingTodoRequest) {
		
		if(!Util.isNull(todoRequest.getTitle())){
			existingTodoRequest.setTitle(todoRequest.getTitle());
		}
		
		if(!Util.isNull(todoRequest.getDescription())){
			existingTodoRequest.setDescription(todoRequest.getDescription());
		}
		
		if(!Util.isNull(todoRequest.getIsDeleted())){
			existingTodoRequest.setIsDeleted(todoRequest.getIsDeleted());
		}		
		
		existingTodoRequest.setUpdatedAt(Util.getCurrentTimeStamp());
		
		return existingTodoRequest;
	}

	@Override
	@Transactional
	public Map<String, Object> getTodoList(String status,
			Integer limit, Integer offset, String sortBy, String sortType,
			String query) {
		return iTodoDao.getTodoList(status,limit,offset,sortBy,sortType,query,sessionFactory);
	}

	

}
