package com.gaoyanshan.bysj.project.schedule;

import com.gaoyanshan.bysj.project.entity.GithubLog;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.repository.GithubLogRepository;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

/**
 * <pre>类名: githubScheduleService</pre>
 * <pre>描述: github提交记录跟踪调度器</pre>
 * <pre>日期: 19-4-5 上午11:27</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Component
public class GithubScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(GithubScheduleService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GithubLogRepository githubLogRepository;

    /**
     * github提交记录跟踪器
     */

    @Async
    //@Scheduled(cron = "0/5 * * * * ? ")
//    @Scheduled(cron = "0 0/10 * * * *")
    public void githubCommitTask(){
        logger.info(new Date()+":github状态调度器执行");
        List<Project> projectList = projectRepository.findAll();
        final CountDownLatch countDownLatch = new CountDownLatch(projectList.size());
        for (Project project : projectList){
            if (project.getGithubUrl() == null || project.getGithubUrl() == "")
            {
                countDownLatch.countDown();
                continue;
            }
            if (project.getGithubUsername() == "" || project.getGithubPassword()==""){
                countDownLatch.countDown();
                continue;
            }
            GithubTaskRunable runable = new GithubTaskRunable(countDownLatch,project);
            runable.run();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("锁等待失败");
        }
    }


    class GithubTaskRunable implements Runnable {

        private CountDownLatch countDownLatch;

        private Project project;

        public GithubTaskRunable(CountDownLatch countDownLatch,Project project) {
            this.countDownLatch = countDownLatch;
            this.project = project;
        }


        @Override
        public void run() {
            try{
                logger.info("项目ID：{}",project.getId());
                GitHubBuilder gitHubBuilder = new GitHubBuilder();
                gitHubBuilder.withPassword(project.getGithubUsername(),project.getGithubPassword());
                GitHub gitHub = gitHubBuilder.build();
                GHRepository ghRepository = gitHub.getRepository(getReposName(project.getGithubUrl()));
                List<GHCommit> ghCommits = ghRepository.listCommits().asList();
                for (GHCommit ghCommit : ghCommits){
                    String sha = ghCommit.getSHA1();
                    if (githubLogRepository.findBySha(sha) != null)
                        continue;
                    Date commitTime = ghCommit.getCommitDate();
                    String committer = ghCommit.getCommitter().getLogin();
                    String commitMessage = ghCommit.getCommitShortInfo().getMessage();
                    GithubLog githubLog = new GithubLog();
                    githubLog.setSha(sha);
                    githubLog.setProjectId(project.getId());
                    githubLog.setCommitDate(commitTime);
                    githubLog.setCommitUserName(committer);
                    githubLog.setCommitMessage(commitMessage);
                    githubLog.setUrl(ghCommit.getHtmlUrl().toString());
                    logger.info("url:{}",ghCommit.getHtmlUrl());
                    githubLogRepository.save(githubLog);

                    System.out.println(githubLog);
                }

            }catch (Exception e){
                logger.error("保存提交记录时失败:{}",e.getMessage());
            }finally {
                countDownLatch.countDown();
            }

        }
    }

    /**
     * 获得repos地址
     * @param url
     * @return
     */
    public String getReposName(String url){
        String[] tokens = url.split("/");
        return tokens[3]+"/"+tokens[4];
    }
}
