package cn.pconline.pcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import cn.pconline.pcloud.admin.app.ApplicationInitial;

@SpringBootApplication
//@EnableCaching // 缓存注解需要
@MapperScan(basePackages = "cn.pconline.pcloud.base.dao")
public class PCloudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(PCloudApplication.class);
        springApplication.addListeners(new ApplicationInitial());
        springApplication.run(args);
	}

}
