package com.gaoyanshan.bysj.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gaoyanshan.bysj.project.repository.UserTaskRepositiry;
import com.gaoyanshan.bysj.project.util.DateUtil;
import com.gaoyanshan.bysj.project.util.MailUtil;
import com.hankcs.hanlp.HanLP;
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

    @Test
    public void testHanlp(){
        String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
                "算法可以宽泛的分为三类，\n" +
                "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
                "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
                "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
        List<String> sentenceList = HanLP.extractSummary(document, 1);
        System.out.println(sentenceList);


        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);

    }


}
