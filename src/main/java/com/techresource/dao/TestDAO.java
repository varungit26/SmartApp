package com.techresource.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TestDAO implements Serializable {

	private static final long serialVersionUID = -3649860441514124521L;

	final static Logger logger = Logger.getLogger(TestDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public String getRole(String name) {

		logger.info("UserName : " + name);
		Session session = sessionFactory.getCurrentSession();

		String userRole = null;
		List<String> userList = new ArrayList<String>();
		userList = session.createQuery("select role from Employee where name ='" + name + "'").list();
		if (userList.size() == 0) {
			logger.info("Wrong Username or Password!");
		} else {
			logger.info("UserName exists");
			userRole = userList.get(0);
		}
		return userRole;
	}

}
