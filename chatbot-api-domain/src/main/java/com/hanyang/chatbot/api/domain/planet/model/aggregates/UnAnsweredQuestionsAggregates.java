package com.hanyang.chatbot.api.domain.planet.model.aggregates;

import com.hanyang.chatbot.api.domain.planet.model.res.RespData;

/**
 * 未回答问题的聚合信息
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

}