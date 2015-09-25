package main.java.com.marine.frame.test;

import main.java.com.marine.frame.base.BaseAction;
import main.java.com.marine.frame.test.impl.LoginImpl;

import org.springframework.beans.factory.annotation.Autowired;

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
