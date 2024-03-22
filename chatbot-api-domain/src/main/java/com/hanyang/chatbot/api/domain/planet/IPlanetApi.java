package com.hanyang.chatbot.api.domain.planet;

import com.hanyang.chatbot.api.domain.planet.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * API 接口
 */
public interface IPlanetApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}
