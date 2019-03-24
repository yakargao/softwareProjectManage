package com.gaoyanshan.bysj.project.config.shiro;

import com.alibaba.druid.support.json.JSONUtils;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;

import java.nio.charset.Charset;

/**
 * <pre>类名: StringRedisSerializer</pre>
 * <pre>描述: 序列化器</pre>
 * <pre>日期: 19-3-20 下午3:08</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class StringRedisSerializer implements RedisSerializer<Object> {


    private final Charset charset = Charset.forName("UTF-8");

    private final String target = "\"";

    private final String replacement = "";


    @Override
    public byte[] serialize(Object o) throws SerializationException {
        String string = JSONUtils.toJSONString(o);
        if (string == null) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
