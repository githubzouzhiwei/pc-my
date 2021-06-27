package cn.pconline.pcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCaching // 缓存注解需要
@EnableCircuitBreaker // 启动熔断机制
@MapperScan(basePackages = "cn.pconline.pcloud.base.dao")
public class PCloudApplication {

	public static void main( String[] args ) {
		SpringApplication.run( PCloudApplication.class, args );
	}
}
