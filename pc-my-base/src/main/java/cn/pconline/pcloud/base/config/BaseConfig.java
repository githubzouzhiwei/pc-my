package cn.pconline.pcloud.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import cn.pconline.framework.util.SpringCtxUtils;

/**
 * 应用spring主配置
 * 
 * @author pconline
 *
 */
@Configuration
public class BaseConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SpringCtxUtils configSpringCtxUtils() {
		return new SpringCtxUtils();
	}

}
