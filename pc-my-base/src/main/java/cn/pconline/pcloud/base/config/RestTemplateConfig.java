package cn.pconline.pcloud.base.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Http工具模板
 * @author jzc
 *
 */
@Configuration
public class RestTemplateConfig {
	
	@Autowired
	BaseProperties baseProperties;

    @Bean
    @Primary
    //@LoadBalanced // 开启客户端负载均衡 Ribbon
    public RestTemplate restTemplate(){
    	SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);
        
    	RestTemplate restTemplate = new RestTemplate(factory);
        
        return restTemplate;
    }
    
    @Bean(name = "restTemplateProxy")
    public RestTemplate restTemplateProxy(){
    	SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(15000);
        
        // 设置代理
        SocketAddress address = new InetSocketAddress(baseProperties.getProxyIp(), baseProperties.getProxyPort());
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        factory.setProxy(proxy);
        
    	RestTemplate restTemplate = new RestTemplate(factory);
        
        return restTemplate;
    }

}
