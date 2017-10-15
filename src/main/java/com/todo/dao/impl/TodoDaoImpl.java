package com.todo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.constants.CommonConstants;
import com.todo.bean.TodoTransaction;
import com.todo.dao.ITodoDao;
/**
 * 
 * @author rajesh
 *
 */
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
		
		Criteria todoListCountCriteria=   sessionFactory.getCurrentSession().createCriteria(TodoTransaction.class)
				.add(Restrictions.eq("isDeleted", false));
		
	
		if(!Util.isNull(status)){
			
			if(status.equals("Completed")){
				todoListCriteria.add(Restrictions.eq("statusId", CommonConstants.TWO));
				todoListCountCriteria.add(Restrictions.eq("statusId", CommonConstants.TWO));


			}else if(status.equals("Pending")){
				todoListCriteria.add(Restrictions.eq("statusId", CommonConstants.ONE));
				todoListCountCriteria.add(Restrictions.eq("statusId", CommonConstants.ONE));

			}
		}
		
		if(!Util.isNull(query)){
			todoListCriteria.add(Restrictions.ilike("title", "%"+query+"%"));
			todoListCountCriteria.add(Restrictions.ilike("title", "%"+query+"%"));

		}
		
		if(!Util.isNull(sortBy)){
			
			if(!Util.isNull(sortType)&&sortType.equalsIgnoreCase("desc")){
				todoListCriteria.addOrder(Order.desc(sortBy));
			}else{
				todoListCriteria.addOrder(Order.asc(sortBy));

			}
		}
		
		todoListCountCriteria.setProjection(Projections.count("id"));
		
		if(!Util.isNull(limit)){
			todoListCriteria.setMaxResults(limit);
		}
		
		if(!Util.isNull(offset)){
			todoListCriteria.setFirstResult(offset);
		}
		
		List<TodoTransaction> todoList = todoListCriteria.list();
		Long todoListCount =  (Long) todoListCountCriteria.uniqueResult();

		todoListMap.put("list", todoList);
		todoListMap.put("count", todoListCount);

		return todoListMap;
	}

}
