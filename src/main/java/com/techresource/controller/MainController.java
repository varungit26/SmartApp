package com.techresource.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techresource.constants.EmpRestURIConstants;
import com.techresource.service.LoginService;

@RestController
public class MainController {

	final static Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	LoginService loginService;

	/*
	 * @RequestMapping(value = { "", "/", "welcome" }) public ModelAndView
	 * welcome() {
	 * 
	 * ModelAndView model = new ModelAndView("welcome"); return model; }
	 */

	
	@RequestMapping(value = EmpRestURIConstants.getAddress, method = RequestMethod.POST)
	public String getAddress() {

		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		logger.info(inet.getHostAddress());
		return inet.getHostAddress();
	}

	@RequestMapping(value = EmpRestURIConstants.getRole, method = RequestMethod.POST)
	public String getUserRole() {
		
		return loginService.getUserRole("David");
		
	}
}
