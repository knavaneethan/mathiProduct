package com.mathi.configuration;

import java.util.Set;

import javax.transaction.Transactional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathi.dao.RolesPermissionDao;
import com.mathi.dao.UserDao;
import com.mathi.dao.UserRoleDao;
import com.mathi.entity.User;

@Transactional
@Service
public class MyCustomRealm 
extends JdbcRealm 
{
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private UserRoleDao useRoleDao;
	
	@Autowired
    private RolesPermissionDao rolesPermissionDao;
	
	  @Override
	  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    // identify account to log to
	    UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
	    final String username = userPassToken.getUsername();

	    if (username == null) {
	      System.out.println("Username is null.");
	      return null;
	    }

	    // read password hash and salt from db
	    User user=null;

			user = userDao.getUserByEmail(username);
			

	    if (user == null) {
	      System.out.println("No account found for user [" + username + "]");
	      return null;
	    }

	    // return salted credentials
	    SaltedAuthenticationInfo info = new MySaltedAuthentificationInfo(username, user.getPassword(), user.getSalt());

	    return info;
	  }
	  
	  @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
	        String email = pc.getPrimaryPrincipal() + "";
	        Set<String> userRoles=null;
	        Set<String> rolePermissions=null;
	        if(email!=null){

					userRoles = useRoleDao.getUserRolesByEmail(email);
					if(userRoles!=null&userRoles.size()>0){
						rolePermissions = rolesPermissionDao.getPermissionsByRoleNames(userRoles);
					}

			}
	        
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(userRoles);
            info.setStringPermissions(rolePermissions);
            return info;
	}	  
	
}