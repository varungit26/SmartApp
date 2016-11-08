package com.techresource.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techresource.dao.LoginDAO;
import com.techresource.dao.TestDAO;

@Service
public class LoginService implements Serializable {

	private static final long serialVersionUID = 1425222646694045964L;

	final static Logger logger = Logger.getLogger(LoginService.class);

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private TestDAO testDAO;

	public String getUserRole(String name) {
		return testDAO.getRole(name);
	}
}
