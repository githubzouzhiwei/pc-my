package cn.pconline.pcloud.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.pconline.pcloud.admin.interceptor.LoggedPostInterceptor;
import cn.pconline.pcloud.base.config.BaseProperties;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	  //@Autowired
	  //private LoggedPostInterceptor loggedPostInterceptor;
	 @Autowired
	 BaseProperties baseProperties;
	  
	  /**
	   * 静态资源映射
	   */
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  // 静态资源文件映射
	      //registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	      // 生成的pdf文件保存在/data/chunya/pdf/目录下，访问：http://www.x.com/data/report/xxx.pdf
	      registry.addResourceHandler("/data/report/**").addResourceLocations("file:" + baseProperties.getFilePath());
	      registry.addResourceHandler("/data/jpg/**").addResourceLocations("file:" + baseProperties.getFilePath().replace("/pdf/", "/jpg/"));
	      registry.addResourceHandler("/data/webp/**").addResourceLocations("file:" + baseProperties.getFilePath().replace("/pdf/", "/webp/"));
	      registry.addResourceHandler("/data/png/**").addResourceLocations("file:" + baseProperties.getFilePath().replace("/pdf/", "/png/"));
	      registry.addResourceHandler("/data/jpeg/**").addResourceLocations("file:" + baseProperties.getFilePath().replace("/pdf/", "/jpeg/"));
	      registry.addResourceHandler("/data/gif/**").addResourceLocations("file:" + baseProperties.getFilePath().replace("/pdf/", "/gif/"));
	  }
	 
	  /**
	   * 添加拦截器
	   */
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	      //registry.addInterceptor(loggedPostInterceptor)
	      //.addPathPatterns("/admin/**")
	      //.excludePathPatterns("/admin/logout", "/admin/index", "/admin/welcome", "/admin/**/index", "/admin/**/list");
	  }
}
