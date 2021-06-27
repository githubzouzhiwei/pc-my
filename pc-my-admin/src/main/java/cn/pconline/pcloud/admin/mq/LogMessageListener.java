package cn.pconline.pcloud.admin.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import cn.pconline.framework.util.StringUtils;
import cn.pconline.pcloud.admin.service.system.OperateLogService;

/**
 * 操作日志消息监听器
 * @author jzc
 *
 */
@Component
public class LogMessageListener implements MessageListener {

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private OperateLogService operateLogService;
 
    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<String> valueSerializer = redisTemplate.getStringSerializer();
        String value = valueSerializer.deserialize(message.getBody());
        
        if(StringUtils.isNotBlank(value)){
        	operateLogService.loggedPostOperate(value);
        }
    }

}
