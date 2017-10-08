package com.mathi.configuration;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfiguration {
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
