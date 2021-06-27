package cn.pconline.pcloud.admin.app;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.pconline.pcloud.admin.service.system.ShiroFilterService;

/**
 * 应用启动时初始化
 * @author jzc
 *
 */
public class ApplicationInitial implements ApplicationListener<ContextRefreshedEvent> {

	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ShiroFilterService shiroFilterService = contextRefreshedEvent.getApplicationContext().getBean(ShiroFilterService.class);
		shiroFilterService.resetFilterChain(); // 重新设置过滤器URL映射授权
    }

}
