package cn.pconline.pcloud.admin.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import cn.pconline.pcloud.admin.mq.LogMessageListener;
import cn.pconline.pcloud.base.util.RedisKey;

/**
 * 消息订阅配置类
 * @author jzc
 *
 */
//@Configuration
//@AutoConfigureAfter({LogMessageListener.class})
public class RedisSubscriberConfig {
	/**
     * 消息监听适配器，注入接受消息方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(LogMessageListener receiver) {
        return new MessageListenerAdapter(receiver); // 当没有继承MessageListener时需要设置方法名参数，如"onMessage"
    }
 
    /**
     * 创建消息监听容器
     *
     * @param redisConnectionFactory
     * @param messageListenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic(RedisKey.REDIS_MQ_ADMIN_LOG));
        return redisMessageListenerContainer;
    }

}
