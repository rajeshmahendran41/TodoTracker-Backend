package com.todo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.todo.bean.TodoTransaction;
import com.todo.dao.ITodoDao;

@Repository
public class TodoDaoImpl implements ITodoDao {


	@Override
	public TodoTransaction getTodo(Long id, SessionFactory sessionFactory) {
		return (TodoTransaction)sessionFactory.getCurrentSession().createCriteria(TodoTransaction.class)
				.add(Restrictions.eq("id", id))
				.add(Restrictions.eq("isDeleted",false)).uniqueResult();
	}

	@Override
	public TodoTransaction saveOrUpdateTodo(TodoTransaction todoRequest,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(todoRequest);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return todoRequest;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getTodoList(String status,
			Integer limit, Integer offset, String sortBy, String sortType,
			String query,SessionFactory sessionFactory) {
		
		Map<String, Object> todoListMap = new HashMap<>();
		
		Criteria todoListCriteria=   sessionFactory.getCurrentSession().createCriteria(TodoTransaction.class)
		.add(Restrictions.eq("isDeleted", false));
		
	
		if(!Util.isNull(status)){
			
			if(status.equals("Completed")){
				todoListCriteria.add(Restrictions.eq("status", 2));

			}else if(status.equals("Pending")){
				todoListCriteria.add(Restrictions.eq("status", 1));

			}
		}
		
		if(!Util.isNull(query)){
			todoListCriteria.add(Restrictions.ilike("title", "%"+query+"%"));
		}
		
		Criteria todoListCountCriteria = todoListCriteria ;
		todoListCountCriteria.setProjection(Projections.count("id"));
		
		if(!Util.isNull(limit)&&!Util.isNull(offset)){
			todoListCriteria.setMaxResults(limit);
			todoListCriteria.setFirstResult(offset);
		}
		
		List<TodoTransaction> todoList = todoListCriteria.list();
		Long todoListCount =  (Long) todoListCountCriteria.uniqueResult();

		todoListMap.put("list", todoList);
		todoListMap.put("count", todoListCount);

		return todoListMap;
	}

}
