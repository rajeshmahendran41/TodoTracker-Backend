package com.todo.service.impl;

import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.constants.CommonConstants;
import com.constants.TodoConstants;
import com.todo.bean.TodoTransaction;
import com.todo.dao.ITodoDao;
import com.todo.service.ITodoService;
/**
 * 
 * @author rajesh
 *
 */
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
			
			Util.throwException(TodoConstants.ID_CANT_BE_NULL);
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
			todoRequest.setStatusId(CommonConstants.ONE);
		}
		
		validateData(todoRequest);
		
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
		
		if(!Util.isNull(todoRequest.getStatusId())){
			existingTodoRequest.setStatusId(todoRequest.getStatusId());
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
	
	public void validateData(TodoTransaction todoRequest){
		if(Util.isNull(todoRequest.getTitle())){
			Util.throwException(TodoConstants.TITLE_CANT_BE_NULL);
		}else if(Util.isNull(todoRequest.getDescription())){
			Util.throwException(TodoConstants.DESCRIPTION_CANT_BE_NULL);
		}
	}

	

}
