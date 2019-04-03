package com.gaoyanshan.bysj.project.util;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>类名: QiniuUtil</pre>
 * <pre>描述: 七牛云平台工具工具类</pre>
 * <pre>日期: 19-4-1 下午8:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Component
public class QiniuUtil {


    @Value("${spring.qiniuyun.accessKey}")
    private String accessKey;

    @Value("${spring.qiniuyun.secretKey}")
    private String secretKey;

    @Value("${spring.qiniuyun.bucket}")
    private String bucket;

    public String getSimpleToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }


}
