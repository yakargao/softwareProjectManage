package com.gaoyanshan.bysj.project.dynamic.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * <pre>类名: DynamicContent</pre>
 * <pre>描述: 个人动态内容</pre>
 * <pre>日期: 19-3-29 下午8:18</pre>
 * <pre>作者: ljianf</pre>
 */
@Table(name = "operation_log")
@Entity
public class DynamicContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dynamic_event_name")
    private String dynamicEventName;

    @Column(name = "create_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    public static DynamicContent getNewInstance(){
        return new DynamicContent();
    }

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取userId
     *
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置userId
     *
     * @param userId userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取dynamicEventName
     *
     * @return dynamicEventName
     */
    public String getDynamicEventName() {
        return dynamicEventName;
    }

    /**
     * 设置dynamicEventName
     *
     * @param dynamicEventName dynamicEventName
     */
    public void setDynamicEventName(String dynamicEventName) {
        this.dynamicEventName = dynamicEventName;
    }

    /**
     * 获取createTime
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime
     *
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
