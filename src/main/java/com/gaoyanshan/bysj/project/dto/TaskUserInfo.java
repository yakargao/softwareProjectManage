package com.gaoyanshan.bysj.project.dto;

/**
 * <pre>类名: TaskUserInfo</pre>
 * <pre>描述: 任务级别的用户信息</pre>
 * <pre>日期: 19-3-29 下午10:27</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class TaskUserInfo extends UserInfo{
    private int type ;

    public TaskUserInfo(int id, String email, String name, int type) {
        super(id, email, name);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
