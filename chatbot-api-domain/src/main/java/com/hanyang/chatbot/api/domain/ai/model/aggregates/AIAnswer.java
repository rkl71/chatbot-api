package com.hanyang.chatbot.api.domain.ai.model.aggregates;

import com.hanyang.chatbot.api.domain.ai.model.vo.Choices;

import java.util.List;

/**
 * AI 回答
 */
public class AIAnswer {

    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObject(){
        return this.object;
    }
    public void setCreated(int created){
        this.created = created;
    }
    public int getCreated(){
        return this.created;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setChoices(List<Choices> choices){
        this.choices = choices;
    }
    public List<Choices> getChoices(){
        return this.choices;
    }


}