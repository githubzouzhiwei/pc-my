package cn.pconline.pcloud.admin.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {
	
	@Value("${app.domain:/}")
	private String domain;

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public Realm realm() {
		return new MyRealm();
	}

	/**
	 * 用户授权信息Cache
	 */
	@Bean(name = "shiroCacheManager")
	@ConditionalOnMissingBean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	/**
	 * 认证绑定
	 * @param realm
	 * @return
	 */
	@Bean(name = "securityManager")
	@ConditionalOnMissingBean
	public DefaultSecurityManager securityManager(MyRealm realm) {
		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setRealm(realm);
		// sm.setCacheManager(cacheManager());
		return sm;
	}

	/**
	 * 授权凭证（启动项目时加载）
	 * 对应 realm.doGetAuthorizationInfo()
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	//@DependsOn("securityManager")
	//@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl(domain + "admin/login");
		shiroFilter.setSuccessUrl(domain + "admin/index");
		shiroFilter.setUnauthorizedUrl(domain + "admin/permission/died"); // 没权限时跳转至该页面
		
		// anon、authc、user对应realm.doGetAuthenticationInfo(..)登录认证
		// perms、roles、ssl、est、port对应realm.doGetAuthorizationInfo(..)授权认证
		
		// 设置过滤器链接集合
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(); // 注意：Map要支持顺序，授权配置后出
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
	
}
