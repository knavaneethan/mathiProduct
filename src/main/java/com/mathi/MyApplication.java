package com.mathi;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mathi.configuration.MyCustomRealm;
@SpringBootApplication
public class MyApplication {  
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
    }    
	
	 @ExceptionHandler(UnauthenticatedException.class)
	    @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    public void handleException(UnauthenticatedException e) {
		 System.out.println("UNAUTHORIZED");
	 }

	    @ExceptionHandler(AuthorizationException.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN)
	    public void handleException(AuthorizationException e) {
	    	System.out.println("FORBIDDEN");
	    }
	    
	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN)
	    public void handleException(Exception e) {
	    	System.out.println("Exception");
	    }


	    @Bean
	    public Realm realm() {

	        // uses 'classpath:shiro-users.properties' by default
	    	final MyCustomRealm realm = new MyCustomRealm();
	        realm.setCredentialsMatcher(credentialsMatcher());

	        // Caching isn't needed in this example, but we can still turn it on
	        realm.setCachingEnabled(true);
	        realm.init();
	        return realm;
	    }
	    @Bean(name = "credentialsMatcher")
	    public HashedCredentialsMatcher credentialsMatcher() {
	        final HashedCredentialsMatcher credentialsMatcher = new Sha256CredentialsMatcher();
	        credentialsMatcher.setStoredCredentialsHexEncoded(false);
	        credentialsMatcher.setHashIterations(1024);
	        return credentialsMatcher;
	    }
	    @Bean
	    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
	        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
	        // use permissive to NOT require authentication, our controller Annotations will decide that
	        chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
	        return chainDefinition;
	    }

	    
	 // enable shiro annotations
	    @DependsOn("lifecycleBeanPostProcessor")
	    @Bean
	    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
	        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
	        proxyCreator.setProxyTargetClass(true);
	        return proxyCreator;
	    }

	    // enable shiro annotations
	    @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
	        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
	        
	        advisor.setSecurityManager(securityManager());
	        return advisor;
	    }
	    
	    @Bean(name = "securityManager")
	    public DefaultWebSecurityManager securityManager() {
	        final DefaultWebSecurityManager securityManager
	                = new DefaultWebSecurityManager();
	        securityManager.setRealm(realm());
	        return securityManager;
	    }
	    
//	    @Bean
//	    public CacheManager cacheManager() {
//	        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
//	        return new MemoryConstrainedCacheManager();
//	    }
}            