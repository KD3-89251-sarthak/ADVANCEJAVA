package com.sunbeam;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class LoginBean {
	
	private String email;
	private String password;
	private User user;
	
	public String getEmail() {
		return email;
	}   
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;		
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public User getUser() {
		return user;
	}
	
	public void validate() {
		
		try(UserDao userDao = new UserDaoImpl()){
			
			User u = userDao.findByEmail(email);
			if(u != null) {
				
				if(u.getPassword().equals(password)) {
					user = u;
				}				
			}else {
			
			user = null;
			}
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	

}
