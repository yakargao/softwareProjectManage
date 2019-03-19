package com.gaoyanshan.bysj.project.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <pre>类名: Md5Util</pre>
 * <pre>描述: md5加密工具</pre>
 * <pre>日期: 19-3-17 上午1:53</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Md5Util {

    public static String passwordToHash(String email,String password){
        String hashAlgorithmName = "md5";//加密方式

        Object crdentials = password ;//密码原值

        ByteSource salt = ByteSource.Util.bytes(email);//以账号作为盐值

        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }
}
