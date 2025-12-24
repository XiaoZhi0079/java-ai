package com.example.javaai.bean;

import lombok.Data;

@Data
public class ChatEntity {

    private String currentUserName;

    private String message;

    private String botMsgId;

    // 用于前端传递是否使用知识库
    private boolean useKnowledgeBase;

}
