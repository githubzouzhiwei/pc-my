package cn.pconline.pcloud.admin.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.pcloud.base.config.BaseProperties;
import cn.pconline.pcloud.base.entity.system.User;
import cn.pconline.pcloud.base.util.RedisKey;

/**
 * POST日志拦截器
 * @author jzc
 *
 */
//@Component
public class LoggedPostInterceptor implements HandlerInterceptor {
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private BaseProperties baseProperties;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
    		Object o) throws Exception {
        try {
        	if ("POST".equals(httpServletRequest.getMethod())) {
        		Subject subject = SecurityUtils.getSubject();
        		User user = (User) subject.getPrincipal();
        		if(user == null){
        			return true;
        		}
        		String contentType = httpServletRequest.getContentType();
        		if(contentType == null) {
        			contentType = "";
        		}
        		String uri = httpServletRequest.getRequestURI();
        		JSONObject json = new JSONObject();
        		json.put("account", user.getAccount());
        		json.put("uri", uri);
        		
        		// 记录所有POST操作
        		if(contentType.startsWith("application/x-www-form-urlencoded")){
                    Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
                    int size = parameterMap == null ? 0 : parameterMap.size();
                    if(size > 0) {
	                    Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
	                    int i = 0;
	                    StringBuilder datas = new StringBuilder();
	                    for (Map.Entry<String, String[]> entry : entries) {
	                    	datas.append(entry.getKey()).append("=").append(String.join(",", entry.getValue()));
	                    	if(++i < size){
	                    		datas.append("&");
	                    	}
	                    }
	                    json.put("params", datas.toString());
                    }
        		} else if(contentType.startsWith("application/json")){ // ajax{contentType:} -> (@RequestBody)
        			// 重新封装Request，因InputStream只能被读取一次
        			PostBodyRequestWrapper requestWrapper = new PostBodyRequestWrapper(httpServletRequest);
        			httpServletRequest = (HttpServletRequest) requestWrapper;
        			json.put("params", requestWrapper.getBodyString());
        		} else {
        			System.out.println("## 未识别的POST请求：" + uri);
        			return true;
        		}
        		
        		// 写入队列
        		this.sendMessage(json.toJSONString());
        	}
        	
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
    		ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
    		Exception e) throws Exception {
    	 String uri = httpServletRequest.getRequestURI();
    	 String st = httpServletRequest.getParameter("st");
    	 
    	 if("GET".equals(httpServletRequest.getMethod()) && uri.startsWith("/admin/login")
     			&& st != null && st.length() > 30) {
     		// 记录登录日志
    		Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getPrincipal();
			if(user == null){
				return;
			}
			String contentType = httpServletRequest.getContentType();
			if(contentType == null) {
				contentType = "";
			}
			
			JSONObject json = new JSONObject();
			json.put("account", user.getAccount());
			json.put("uri", uri);
     		json.put("params", "st=" + st);
     		
     		this.sendMessage(json.toJSONString());
     	}
    	
    }
    
    /**
     * 发送消息
     * @param value
     */
    private void sendMessage(String value) {
        try {
            redisTemplate.convertAndSend(baseProperties.getApplicationName() + RedisKey.REDIS_MQ_ADMIN_LOG, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
