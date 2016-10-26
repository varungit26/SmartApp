package com.techresource.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.core.util.InetAddressConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

	/*
	 * @RequestMapping(value = { "", "/", "welcome" }) public ModelAndView
	 * welcome() {
	 * 
	 * ModelAndView model = new ModelAndView("welcome"); return model; }
	 */

	@RequestMapping(value = "getAddress")
	public String getAddress() {
		
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(inet.getHostAddress());
		return inet.getHostAddress();
	}
}
