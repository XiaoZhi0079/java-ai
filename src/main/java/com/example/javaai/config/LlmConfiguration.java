package com.example.javaai.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "llm.platform")
public class LlmConfiguration {
    private  dashscope dashscope;
    private  modelscope modelscope;
    private  iflow iflow;

    @Data
    public static class dashscope {
        private String baseurl;
        private String apikey;
        private Options options;
    }
    @Data
    public static class modelscope {
        private String baseurl;
        private String apikey;
        private Options options;
    }
    @Data
    public static class iflow {
        private String baseurl;
        private String apikey;
        private Options options;
    }
    @Data
    public static class Options {
        private String model;
        private double temperature;
        private double maxTokens;
    }
}
