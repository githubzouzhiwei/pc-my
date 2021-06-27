package cn.pconline.pcloud.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.pconline.framework.enums.ResponseCodeEnum;
import cn.pconline.framework.util.ResponseResult;

@RestController
public class RedisController {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/redis/delete/{key}")
	public ResponseResult delete(@PathVariable String key){
		if(key.contains("*")){
			return ResponseResult.build(ResponseCodeEnum.ERROR, "KEY不符合规范");
		}
		
		if(stringRedisTemplate.hasKey(key)){
			stringRedisTemplate.delete(key);
			return ResponseResult.build(ResponseCodeEnum.SUCCESS);
		}
		
        return ResponseResult.build(ResponseCodeEnum.ERROR, "KEY不存在");
	}
	
	/**
	 * 根据前缀删除缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/redis/delete/prefix/{key}")
	public ResponseResult deleteByPrefix(@PathVariable String key){
		if(key.contains("*")){
			return ResponseResult.build(ResponseCodeEnum.ERROR, "KEY不符合规范");
		}
		
		// 删除前缀相同的缓存
		if(key.length() >= 10){
			Set<String> keys = stringRedisTemplate.keys(key + "*"); // 很慢，不建议
	        if (!CollectionUtils.isEmpty(keys)) {
	            stringRedisTemplate.delete(keys);
	            return ResponseResult.build(ResponseCodeEnum.SUCCESS);
	        }
		} else {
			return ResponseResult.build(ResponseCodeEnum.ERROR, "KEY的长度不能短于10");
		}
        
        return ResponseResult.build(ResponseCodeEnum.ERROR, "KEY不存在");
	}
}
