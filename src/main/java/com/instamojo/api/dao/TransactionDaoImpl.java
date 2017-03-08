package com.instamojo.api.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.Transactions;
import com.ysyt.bean.UserAccomodationMapping;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

	@Override
	public Transactions createOrder(Transactions transaction,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(transaction);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return transaction;
		}

	@Override
	public Transactions getOrderDetails(String orderId,
			SessionFactory sessionFactory) {
		
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Transactions.class)
				.add(Restrictions.eq("isDeleted",false));
				
				if(!Util.isNull(orderId)){
					criteria.add(Restrictions.eq("orderId", orderId));
				}
				
				return (Transactions) criteria.uniqueResult();		
	}

	@Override
	public UserAccomodationMapping createUpdateUserAccomodationMapping(
			UserAccomodationMapping userAccomodationMapping,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(userAccomodationMapping);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return userAccomodationMapping;
	}



}