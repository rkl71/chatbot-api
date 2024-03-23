package com.hanyang.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.hanyang.chatbot.api.domain.ai.IOpenAI;
import com.hanyang.chatbot.api.domain.planet.IPlanetApi;
import com.hanyang.chatbot.api.domain.planet.model.aggregates.UnAnsweredQuestionsAggregates;
import com.hanyang.chatbot.api.domain.planet.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * 任务体
 */
public class ChatbotTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(ChatbotTask.class);

    private String groupName;
    private String groupId;
    private String cookie;
    private String openAiKey;
    private boolean silenced;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IPlanetApi planetApi, IOpenAI openAI, boolean silenced) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.planetApi = planetApi;
        this.openAI = openAI;
        this.silenced = silenced;
    }

    private IPlanetApi planetApi;

    private IOpenAI openAI;

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("{} 随机打烊中...", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = planetApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("{} 检索结果：{}", groupName, JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("{} 本次检索未查询到待会答问题", groupName);
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(topics.size() - 1);
            String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());
            // 3. 问题回复
            boolean status = planetApi.answer(groupId, cookie, topic.getTopic_id(), answer, silenced);
            logger.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            logger.error("{} 自动回答问题异常", groupName, e);
        }
    }


}
