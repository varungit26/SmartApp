package com.techresource.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techresource.model.User;

@Repository
public class UserDAO implements Serializable {

	private static final long serialVersionUID = -2468270867016345983L;

	final static Logger logger = Logger.getLogger(UserDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> findAllUsers() {
		
		List<User> userList = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		userList = session.createQuery(" from User ").list();
		return userList;
	}

	public User findById(long id) {

		logger.info("Finding user of userid : " + id);
		List<User> userList = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		userList = session.createQuery(" from User where id = '" + id + "'").list();

		if (!userList.isEmpty())
			return userList.get(0);
		return null;
	}

	public User findByName(String username) {
		
		List<User> userList = new ArrayList<User>();
		logger.info("Get current session for retrieving username");
		Session session = sessionFactory.getCurrentSession();
		userList = session.createQuery(" from User where username = '" + username + "'").list();

		logger.info(userList.toString());
		if (!userList.isEmpty())
			return userList.get(0);
		return null;
	}

	public void saveUser(User user) {

		logger.info("Creating session to save user.");
		Session session = sessionFactory.getCurrentSession();

		logger.info(user.getLastName());
		session.persist(user);
		logger.info("user - " + user.getUserName() + " saved successfully ");
	}

}
