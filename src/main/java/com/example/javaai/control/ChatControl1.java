package com.example.javaai.control;

import com.example.javaai.Pojo.Actor;
import com.example.javaai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")

public class ChatControl1 {

    @Autowired
    @Qualifier("chatClient")
    private ChatClient chatClient1;

    @Autowired
    @Qualifier("Client-qwen-plus")
    private ChatClient Clientqwenplus;

    @Autowired
    @Qualifier("Client-GLM-4.5")
    private ChatClient ClientGLM;

    @Autowired
    @Qualifier("Client-Kimi-K2-Instruct-0905")
    private ChatClient ClientKimiK2;

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;




    record ActorsFilms(String actor, List<String> movies) {
    }

    @RequestMapping("/chat1")
    public ActorsFilms chatcall1(String chatId) {
        ActorsFilms actorsFilms = chatClient1.prompt()
                .user(u -> u.text("Generate the filmography of 5 movies for {actor}.")
                        .param("actor", "成龙"))
                .call()
                .entity(ActorsFilms.class);
        return actorsFilms;
    }


    @RequestMapping("/chat")
    public Actor chatcall(String userinput,String chatId) {
        chatHistoryRepository.save(chatId,"chat");
        return chatClient1.prompt()
                .user(u -> u.text("Generate the filmography of 5 movies for {actor}.")
                        .param("actor", "Tom Hanks"))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(Actor.class);
    }
    @RequestMapping("/chat_image")
    public String chatimage(String userinput, String chatId) {
        return chatClient1.prompt()
                .user(u -> u.text(userinput)
                        .media(MimeTypeUtils.IMAGE_PNG, new ClassPathResource("/multimodal.test.png")))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .content();

    }
    @RequestMapping(value = "/chatstreamqwen",produces = "text/html;charset=utf-8")
    public Flux<String> chatstreamqwen(String userinput,String chatId) {
        chatHistoryRepository.save(chatId,"chat");
        return Clientqwenplus.prompt()
                .user(userinput)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
    @RequestMapping(value = "/chatstreamglm",produces = "text/html;charset=utf-8")
    public Flux<String> chatstreamglm(String userinput,String chatId) {
        chatHistoryRepository.save(chatId,"chat");
        return ClientGLM.prompt()
                .user(userinput)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
    @RequestMapping(value = "/chatstreamkimi",produces = "text/html;charset=utf-8")
    public Flux<String> chatstreamkimi(String userinput,String chatId) {
        chatHistoryRepository.save(chatId,"chat");
        return ClientKimiK2.prompt()
                .user(userinput)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
}




//    @RequestMapping("/chat1")
//    public ChatResponse chatcall1(String userinput, String chatId) {
//        return chatClient1.prompt()
////                .user(u -> u.text("Explain what do you see on this picture?")
////                        .media(MimeTypeUtils.IMAGE_PNG, new ClassPathResource("/multimodal.test.png")))
//                .user(userinput)
//                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
//                .call()
//                .chatResponse();
//    }
//
//    @RequestMapping("/chat2")
//    public ChatClientResponse chatcall2(String userinput, String chatId) {
//        return chatClient1.prompt()
//                .user(userinput)
//                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
//                .call()
//                .chatClientResponse();
//    }
