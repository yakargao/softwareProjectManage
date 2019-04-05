package com.gaoyanshan.bysj.project;

import org.junit.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

/**
 * <pre>类名: Test1</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-5 上午9:51</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class Test1 {

    @Test
   public void testGithub(){

        System.out.println(getReposName("https://github.com/CiachoG/softwareProjectManage"));
        GitHubBuilder gitHubBuilder = new GitHubBuilder();
        gitHubBuilder.withPassword("CiachoG","github022016");
        try {
            GitHub gitHub = gitHubBuilder.build();


            GHRepository ghRepository = gitHub.getRepository("CiachoG/softwareProjectManage");

            List<GHEventInfo> ghEventInfos = ghRepository.listEvents().asList();
            for (GHEventInfo ghEventInfo : ghEventInfos){
                //System.out.println(ghEventInfo);
            }
            List<GHCommit> ghCommits = ghRepository.listCommits().asList();
            for (GHCommit ghCommit : ghCommits){
                System.out.println(ghCommit.getCommitDate());
                System.out.println(ghCommit.getCommitter());
                System.out.println(ghCommit.getCommitShortInfo().getMessage());
            }
            System.out.println(ghCommits.get(0).getCommitter());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getReposName(String url){
       String[] tokens = url.split("/");
       return tokens[3]+"/"+tokens[4];
    }
}
