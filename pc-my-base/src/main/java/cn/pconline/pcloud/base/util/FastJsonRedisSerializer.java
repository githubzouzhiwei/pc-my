package cn.pconline.pcloud.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;


/**
 * 提供给redis使用的通用序列化方式类
 * 代码留存
 * @author tpy
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");//GBK

    static {
        //fastjson在1.2.25及之后的版本，禁用了部分autotype的功能，如果需要反序列化的对象类，必须要配置进白名单中，否则反序列化时会报错
        //com.alibaba.fastjson.JSONException: autoType is not support
        //如下面的配置就是声明了本工程entity目录下的对象，都可以使用fastjson进行反序列化
        ParserConfig.getGlobalInstance().addAccept("cn.pconline.pcloud.base.entity");
        ParserConfig.getGlobalInstance().addAccept("cn.pconline.pcloud.base.vo");
    }

    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }

}
