package com.example.javaai.control;

import com.example.javaai.bean.ChatEntity;

import com.example.javaai.service.ChatService;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    /**
     * 统一的聊天接口
     * @param chatEntity 包含消息和是否使用知识库的标志
     */
    @PostMapping("/send")
    public void chat(@RequestBody ChatEntity chatEntity) {
        // 直接将包含所有信息的实体传递给服务层
        chatService.streamChat(chatEntity);
    }

}
