package com.gaoyanshan.bysj.project.constant;

import com.gaoyanshan.bysj.project.dto.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>类名: Constant</pre>
 * <pre>描述: 常用的常量</pre>
 * <pre>日期: 19-3-21 下午5:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Constant {

    /**
     * 数据库软删除标识
     */
    public static final int DB_UNDELETED = 0;
    public static final int DB_DELETED = 1;


    /**
     * 用户与任务的关联关系
     */
    public static final int CREATE_TYPE = 1;
    public static final int RESPONSE_TYPE = 1;

    /**
     * 任务是否完成
     */

    public static final int TASK_IS_NOT_DONE = 0;
    public static final int TASK_IS_DONE = 1;

    /**
     * 文档类型
     */

    public static HashMap<Integer, String> DOCUMENT_TYPES = new HashMap<Integer, String>() {
        {
            put(1,"普通文档");
            put(2, "需求文档");
            put(3, "方案文档");
            put(4,"技术沉淀");
            put(5,"bug报告");
        }
    };

    /**
     * 文件类型
     */
    public static ArrayList<Types> FILE_TYPES = new ArrayList<Types>(){
        {
            add(new Types(1,"普通文件"));
            add(new Types(2,"技术资料"));
            add(new Types(3,"项目图片"));
            add(new Types(4,"演示文档"));
        }
    };
}
