package com.marine.test;
import org.springframework.beans.factory.annotation.Autowired;

import com.marine.base.BaseAction;
import com.marine.test.impl.LoginImpl;



public class LoginAction extends BaseAction {

	@Autowired
	private LoginImpl loginImpl;
	
	 public String execute() throws Exception   
	    {    
	        return "success";    
	    }
	 
	 public String login(){
		 
		 return "success";
	 }
	  
}
