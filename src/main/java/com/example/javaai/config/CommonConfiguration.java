package com.example.javaai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CommonConfiguration {

    @Autowired
    @Qualifier("dashscopeapi")
    private OpenAiApi dashscopeapi;

    @Autowired
    @Qualifier("modelscopeapi")
    private OpenAiApi modelscopeapi;

    @Autowired
    @Qualifier("iflowapi")
    private OpenAiApi iflowapi;


    @Bean
    public ChatMemory chatMemory(){
        return MessageWindowChatMemory.builder().maxMessages(30).build();
    }


    @Bean("chatClient")
    public ChatClient Chatclient1(OpenAiChatModel baseChatModel){
        return ChatClient
                .builder(baseChatModel)
//                .defaultSystem("你是里美")
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory()).build(),
                        new SimpleLoggerAdvisor()
                )
                .build();
    }
    @Bean("Client-qwen-plus")
    public ChatClient qwenPlus(OpenAiChatModel baseChatModel) {
        return buildChatClient(baseChatModel, dashscopeapi, "qwen-plus");
    }

    @Bean("Client-GLM-4.5")
    public ChatClient glm(OpenAiChatModel baseChatModel) {
        return buildChatClient(baseChatModel, modelscopeapi, "ZhipuAI/GLM-4.5");
    }

    @Bean("Client-Kimi-K2-Instruct-0905")
    public ChatClient kimi(OpenAiChatModel baseChatModel) {
        return buildChatClient(baseChatModel, iflowapi, "kimi-k2");
    }



    private ChatClient buildChatClient(OpenAiChatModel baseChatModel, OpenAiApi api, String modelName) {
        ChatModel chatModel = baseChatModel.mutate()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model(modelName)
                        .temperature(0.8)
                        .build())
                .build();

        return ChatClient.builder(chatModel)
                // .defaultSystem("你是小王") // 可选
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory()).build()
                )
                .build();
    }




//    @Bean("Client-qwen-plus")
//    public ChatClient qwenplus(OpenAiChatModel baseChatModel){
//        ChatModel qwenmodel=baseChatModel.mutate()
//                .openAiApi(dashscopeapi)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("qwen-plus")
//                        .temperature(0.8)
//                        .build())
//                .build();
//        return ChatClient
//                .builder(qwenmodel)
//                .defaultSystem("你是小王")
//                .defaultAdvisors(
////                        new SimpleLoggerAdvisor(),
//                 MessageChatMemoryAdvisor.builder(chatMemory()).build())
//                .build();
//    }
//    @Bean("Client-GLM-4.5")
//    public ChatClient GLM(OpenAiChatModel baseChatModel){
//        ChatModel glmmodel=baseChatModel.mutate()
//                .openAiApi(modelscopeapi)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("ZhipuAI/GLM-4.5")
//                        .temperature(0.8)
//                        .build())
//                .build();
//        return ChatClient
//                .builder(glmmodel)
////                .defaultSystem("你是小王")
//                .defaultAdvisors(
////                        new SimpleLoggerAdvisor(),
//                MessageChatMemoryAdvisor.builder(chatMemory()).build())
//                .build();
//    }
//    @Bean("Client-Kimi-K2-Instruct-0905")
//    public ChatClient kimi(OpenAiChatModel baseChatModel){
//        ChatModel kimimodel=baseChatModel.mutate()
//                .openAiApi(iflowapi)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("kimi-k2")
//                        .temperature(0.8)
//                        .build())
//                .build();
//        return ChatClient
//                .builder(kimimodel)
////                .defaultSystem("你是小王")
//                .defaultAdvisors(
//                        new SimpleLoggerAdvisor(),
//                        MessageChatMemoryAdvisor.builder(chatMemory()).build())
//                .build();
//    }
}
