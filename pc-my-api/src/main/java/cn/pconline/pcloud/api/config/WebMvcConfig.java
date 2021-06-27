package cn.pconline.pcloud.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.pconline.pcloud.api.interceptor.ApiCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private ApiCheckInterceptor apiCheckInterceptor;
	
	/**
	   * 静态资源映射
	   */
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  }
	 
	  /**
	   * 添加拦截器
	   */
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(apiCheckInterceptor)
	      .addPathPatterns("/v*/**");
	  }
	  
}
