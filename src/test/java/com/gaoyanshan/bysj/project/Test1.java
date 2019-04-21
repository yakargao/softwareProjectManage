package com.gaoyanshan.bysj.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaoyanshan.bysj.project.repository.UserTaskRepositiry;
import com.gaoyanshan.bysj.project.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: Test1</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-5 上午9:51</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Test1 {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testTime(){
        System.out.println( DateUtil.getWeekNumOfNow());
        System.out.println(sdf.format(DateUtil.getOneWeekBegin(15)));
        System.out.println(sdf.format(DateUtil.getOneWeekEnd(15)));
        System.out.println(DateUtil.getOneWeekBegin(15).getTime());
    }

}
