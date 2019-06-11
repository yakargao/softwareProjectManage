package com.gaoyanshan.bysj.project.util;


import java.util.Random;

/**
 * <pre>类名: PasswordHelper</pre>
 * <pre>描述: 密码帮助</pre>
 * <pre>日期: 19-4-13 下午10:26</pre>
 * <pre>作者: gaoyanshan</pre>
 */

public class PasswordHelper {

    /**
     * 随机生成密码
     * @param length
     * @return
     */
    public static String generatePassword(int length){
        String password = "";
        Random random = new Random();
        for (int i = 0; i < length; i ++) {
            // 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
            int charOrNum = random.nextInt(2);
            if (charOrNum == 1) {
                // 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
                int temp = random.nextInt(2) == 1 ? 65 : 97;
                password += (char) (random.nextInt(26) + temp);
            } else {
                // 生成随机数字
                password += random.nextInt(10);
            }
        }
        return password;
    }

}
