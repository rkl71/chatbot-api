package com.hanyang.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.hanyang.chatbot.api.domain.ai.IOpenAI;
import com.hanyang.chatbot.api.domain.planet.model.aggregates.UnAnsweredQuestionsAggregates;
import com.hanyang.chatbot.api.domain.planet.model.vo.Topics;
import com.hanyang.chatbot.api.domain.planet.service.PlanetApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private PlanetApi planetApi;
    @Resource
    private IOpenAI openAI;

    // 表达式：cron.qqe2.com
    @Scheduled(cron = "0/15 * * * * ?")
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = planetApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待会答问题");
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());
            // 3. 问题回复
            boolean status = planetApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            logger.error("自动回答问题异常", e);
        }
    }

}
