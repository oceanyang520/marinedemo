package main.java.com.marine.frame.test.impl;

import java.util.ArrayList;
import java.util.List;

import main.java.com.marine.frame.entity.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginImpl {

	  @Autowired  
	    private SessionFactory sessionFactory;  
	      
	    public User getUserByName(String name)  
	    {  
	        Session session = sessionFactory.getCurrentSession();  
	        List<User> list = new ArrayList<User>();   
	        
	        Criteria criteria = session.createCriteria(User.class);    
	        if (!(null == name && "".equals(name)))   
	        {    
	            criteria.add(Restrictions.eq("name", name));    
	        }    
	        list = criteria.list();    
	          
	        if(list != null && list.size() > 0)   
	        {  
	            return list.get(0);    
	        }  
	        return null;    
	    }  
}
