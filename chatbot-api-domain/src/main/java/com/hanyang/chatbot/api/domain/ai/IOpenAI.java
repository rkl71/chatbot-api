package com.hanyang.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * ChatGPT openai 接口
 */
public interface IOpenAI {
    String doChatGPT(String question) throws IOException;
//    String doChatGPT(String openAiKey, String question) throws IOException;
}
