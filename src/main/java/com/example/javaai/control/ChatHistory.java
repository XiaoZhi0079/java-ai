package com.example.javaai.control;


import com.example.javaai.Pojo.MessageVO;
import com.example.javaai.repository.ChatHistoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai/history")
public class ChatHistory {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    //    @RequestMapping("{type}")
//    public List<String> get(@PathVariable("type") String type)
//    {
//        return chatHistoryRepository.get(type);
//    }
    @RequestMapping("/type/{type}")
    public List<String> get(@PathVariable("type") String type) {
        return chatHistoryRepository.get(type);
    }

    @RequestMapping("chat/{chatId}")
    public List<MessageVO> getbyid(@PathVariable("chatId") String chatId) {

        List<Message> messages = chatHistoryRepository.getbyid(chatId);
        return messages.stream().map(MessageVO::new).toList();
    }
}
