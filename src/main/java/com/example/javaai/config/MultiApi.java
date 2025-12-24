package com.example.javaai.config;


import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
public class MultiApi {


    @Autowired
    private  LlmConfiguration llmConfiguration;

    @Bean("dashscopeapi")
    public OpenAiApi dashscopeapi() {
        return OpenAiApi.builder()
                .baseUrl(llmConfiguration.getDashscope().getBaseurl())
                .apiKey(llmConfiguration.getDashscope().getApikey())
                .build();
    }
    @Bean("modelscopeapi")
    public OpenAiApi modelscopeapi() {
        return OpenAiApi.builder()
                .baseUrl(llmConfiguration.getModelscope().getBaseurl())
                .apiKey(llmConfiguration.getModelscope().getApikey())
                .build();
    }

    @Bean("iflowapi")
    public OpenAiApi iflowapi() {
        return OpenAiApi.builder()
                .baseUrl(llmConfiguration.getIflow().getBaseurl())
                .apiKey(llmConfiguration.getIflow().getApikey())
                .build();
    }


    //    @Bean("qwenModel")
//    public OpenAiChatModel qwenModel() {
//        OpenAiApi qwen1Api = OpenAiApi.builder()
//                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode")
//                .apiKey(System.getenv("sk-0ff8034547cc4716a2ac8dfd7618e897"))
//                .build();
//
//        return baseChatModel.mutate()
//                .openAiApi(qwen1Api)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("qwen-omni-turbo-realtime")
//                        .temperature(0.5)
//                        .build())
//                .build();
//    }

//    @Bean("bailianModel")
//    public OpenAiChatModel bailianModel() {
//        OpenAiApi bailianApi = OpenAiApi.builder()
//                .baseUrl("https://api-inference.modelscope.cn")
//                .apiKey(System.getenv("ms-041e1996-c54f-465e-90d8-6beba54959ae"))
//                .build();
//
//        return baseChatModel.mutate()
//                .openAiApi(bailianApi)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("Qwen/Qwen3-Next-80B-A3B-Instruct")
//                        .temperature(0.7)
//                        .build())
//                .build();
//    }
//
//    @Bean("xinliuModel")
//    public OpenAiChatModel xinliuModel() {
//        OpenAiApi xinliuApi = OpenAiApi.builder()
//                .baseUrl("https://apis.iflow.cn")
//                .apiKey(System.getenv("sk-fd5f475b5160d1ca41c9cf54b85beaca"))
//                .build();
//
//        return baseChatModel.mutate()
//                .openAiApi(xinliuApi)
//                .defaultOptions(OpenAiChatOptions.builder()
//                        .model("qwen-vl-max")
//                        .temperature(0.7)
//                        .build())
//                .build();
//    }
}
