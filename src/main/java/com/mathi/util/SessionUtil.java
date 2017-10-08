package com.mathi.util;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mathi.dao.UserDao;
import com.mathi.entity.User;

@Service("sessionUtil")
@Transactional
public class SessionUtil {
	
	@Autowired
    private UserDao userDao;
	
	public Boolean login(User user) {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		System.out.println("current"+currentUser.getPrincipal());
	  	  if (!currentUser.isAuthenticated()) {
	  	    //collect user principals and credentials in a gui specific manner
	  	    //such as username/password html form, X509 certificate, OpenID, etc.
	  	    //We'll use the username/password example here since it is the most common.
	  	    UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(),user.getPassword());
	  	     //this is all you have to do to support 'remember me' (no config - built in!):
	  	    //token.setRememberMe(true);

	  	    try {
	  	        currentUser.login(token);
	  	        System.out.println("User [" + currentUser.getPrincipal().toString() + "] logged in successfully.");
	  	        // save current username in the session, so we have access to our User model
	  	        currentUser.getSession().setAttribute("username", user.getEmail());
	  	        return true;
	  	    } catch (UnknownAccountException uae) {
	  	      System.out.println("There is no user with username of "
	  	                + token.getPrincipal());
	  	    } catch (IncorrectCredentialsException ice) {
	  	      System.out.println("Password for account " + token.getPrincipal()
	  	                + " was incorrect!");
	  	    } catch (LockedAccountException lae) {
	  	      System.out.println("The account for username " + token.getPrincipal()
	  	                + " is locked.  "
	  	                + "Please contact your administrator to unlock it.");
	  	    }
	  	    
	  	  } else {
	  	    return true; // already logged in
	  	  }

	  	  return false;
			
		}
	
	public boolean hasRole(String Role){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser!=null)
		return currentUser.hasRole(Role);
		
		return false;
	}
	
	public boolean hasRoles(Collection<String> roles){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser!=null)
		return currentUser.hasAllRoles(roles);
		
		return false;
	}
	
	public boolean hasPermission(String permission){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser!=null)
		return currentUser.isPermitted(permission);
		
		return false;
	}
	
	public boolean hasPermissions(String... permissions){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		
		if(currentUser!=null)
		return currentUser.isPermittedAll(permissions);
		
		return false;
	}
	
	public void checkPermission(String permission){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();		
		if(currentUser!=null)
		currentUser.checkPermission(permission);
	}
	
	public void checkPermissions(String... permissions){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();		
		if(currentUser!=null)
		currentUser.checkPermissions(permissions);
	}
	
	public void checkRole(String role){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();		
		if(currentUser!=null)
		currentUser.checkRole(role);
	}
	
	public void checkRoles(String... roles){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();		
		if(currentUser!=null)
		currentUser.checkRoles(roles);
	}
	
	public Boolean logout() {
		  org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		  currentUser.logout();
		  return true;
	}
	
	public User getCurrentUser() {
	  org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

	  if (currentUser.isAuthenticated()) {
	    String email = (String) currentUser.getSession().getAttribute("username");
			User user = userDao.getUserByEmail(email);
			return user;
	  } 
	  return null;
	}
}
