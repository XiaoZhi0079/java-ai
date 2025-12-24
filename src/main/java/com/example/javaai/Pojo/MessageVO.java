package com.example.javaai.Pojo;


import lombok.Data;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;

import java.util.List;


@Data
public class MessageVO {
    MessageType role;
    String content;
    public MessageVO(Message  message) {
        this.role = message.getMessageType();
        this.content = message.getText();
    }
}
