package com.gaoyanshan.bysj.project.dynamic.aspect;


import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;

import java.lang.annotation.*;

/**
 * <pre>类名: Dynamic</pre>
 * <pre>描述: 个人动态切点</pre>
 * <pre>日期: 19-3-29 下午8:18</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Dynamic {

    DynamicEventEnum event() default DynamicEventEnum.NULL;

}
