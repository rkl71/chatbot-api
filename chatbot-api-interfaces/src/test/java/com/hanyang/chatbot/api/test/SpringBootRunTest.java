package com.hanyang.chatbot.api.test;

import com.hanyang.chatbot.api.domain.ai.IOpenAI;
import com.hanyang.chatbot.api.domain.planet.IPlanetApi;
import com.alibaba.fastjson.JSON;
import com.hanyang.chatbot.api.domain.planet.model.aggregates.UnAnsweredQuestionsAggregates;
import com.hanyang.chatbot.api.domain.planet.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IPlanetApi planetApi;

    @Resource
    private IOpenAI openAI;


    @Test
    public void test_planetApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = planetApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("帮我写一个java冒泡排序");
        logger.info("测试结果：{}", response);
    }


}
