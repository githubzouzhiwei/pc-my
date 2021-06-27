package cn.pconline.pcloud.base.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.pconline.pcloud.base.config.BaseProperties;

/**
 * POST 请求接口服务类
 * @author jzc
 *
 */
@Service
public class RestApiService {
	
	@Autowired
	@Qualifier("restTemplateProxy")
	RestTemplate restTemplate;
	
	@Autowired
	BaseProperties baseProperties;
	
	/**
	 * GET
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject get(String url, Map<String, Object> paramMap) throws Exception {
        url = buildUrl(url, paramMap); // 组装参数
        
        // headers
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        //requestHeaders.add("token", token);
        
        // HttpEntity
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        
	    // 配置映射类型
	    RequestCallback requestCallback = restTemplate.httpEntityCallback(requestEntity, JSONObject.class);
	    ResponseExtractor<ResponseEntity<JSONObject>> responseExtractor = restTemplate.responseEntityExtractor(JSONObject.class);
	    
	    // 发送请求
	    ResponseEntity<JSONObject> responseEntity = restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);

        return responseEntity.getBody();
    }
	
	/**
	 * POST
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject post(String url, Map<String, Object> paramMap) throws Exception {
        
        // headers
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        //requestHeaders.add("token", token);
        
        // body
        String jsonBody = paramMap == null ? "{}" : JSONObject.toJSONString(paramMap);
        
       // HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonBody, requestHeaders);
        
        // POST
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, requestEntity, JSONObject.class);
        
        return responseEntity.getBody();
    }
	
	/**
     * 拼装url
     * @param uri
     * @param paramMap
     * @return
     */
    public String buildUrl(String uri, Map<String, Object> paramMap){
    	if(uri.contains("?")) {
    		return uri;
    	}
    	
    	StringBuffer stringBuffer = new StringBuffer(uri);
    	
        if(paramMap != null && !paramMap.isEmpty()){
        	stringBuffer.append("?");
        	boolean first = true;
        	
        	for (Entry<String, Object> param : paramMap.entrySet()) {
        		Object paramsValue = param.getValue();
        		//是否传参
        		if(paramsValue != null && !"".equals(paramsValue)) {
            		if(first){
            			first = false;
            		} else {
            			stringBuffer.append("&");
            		}
            		stringBuffer.append(param.getKey()).append("=").append(paramsValue);
            	}
        	}
        }
        
        return stringBuffer.toString();
    }
	
}