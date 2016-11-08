package com.techresource.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO implements Serializable {

	private static final long serialVersionUID = 3494048759073958152L;

	final static Logger logger = Logger.getLogger(LoginDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	
}
