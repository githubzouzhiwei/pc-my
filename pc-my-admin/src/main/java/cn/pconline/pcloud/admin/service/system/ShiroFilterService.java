package cn.pconline.pcloud.admin.service.system;

import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.base.entity.system.Resource;

/**
 * Shiro过滤器服务类
 * @author jzc
 *
 */
@Service
public class ShiroFilterService {
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	/**
	 * 重新设置过滤器URL映射授权
	 * 【注意顺序及通配符】
	 */
	public synchronized void resetFilterChain(){
		// 查询所有资源
		List<Resource> list = resourceService.list();
		if(list == null || list.isEmpty()){
			return;
		}
		try {
			Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
			
			// 获取过滤管理器
			AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
			
			// 清空初始权限配置
			manager.getFilterChains().clear();
			filterChainDefinitionMap.clear();
			
			// 重新加载资源
			filterChainDefinitionMap.put("/admin/login", "anon"); //--不需要登录认证
			filterChainDefinitionMap.put("/admin/loginCheck", "anon");
			filterChainDefinitionMap.put("/admin/task/**", "anon");
			manager.createChain("/admin/login", "anon");
			manager.createChain("/admin/loginCheck", "anon");
			manager.createChain("/admin/task/**", "anon");
			
			for (Resource resource : list) {
				if(resource.getType() == 0 || StringUtils.isBlank(resource.getSourceKey())
						|| StringUtils.isBlank(resource.getSourceUrl())){
					continue;
				}
				//--设置授权认证【注：URL必须是实际存在的，否则会影响其它授权认证】
				filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
				manager.createChain(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
			}
			
			filterChainDefinitionMap.put("/admin/**", "authc"); //--任何请求都需要登录认证【注：这种表达式覆盖面太大，建议放在最后面】
			manager.createChain("/admin/**", "authc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
