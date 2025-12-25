package com.example.javaai;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication



public class JavaAiApplication {

    static {
        // ========== 方案1：SOCKS5代理（V2RayN默认） ==========
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "10808"); // 替换为你的V2RayN SOCKS5端口
        // 若V2RayN设置了代理账号密码，添加以下两行（一般无需）
        // System.setProperty("java.net.socks.username", "你的用户名");
        // System.setProperty("java.net.socks.password", "你的密码");

        // ========== 方案2：HTTP代理（若用V2RayN的HTTP端口） ==========
        // System.setProperty("http.proxyHost", "127.0.0.1");
        // System.setProperty("http.proxyPort", "10809"); // 替换为你的V2RayN HTTP端口
        // System.setProperty("https.proxyHost", "127.0.0.1");
        // System.setProperty("https.proxyPort", "10809");

        // 忽略SSL证书验证（V2RayN代理GitHub时可能需要）
        System.setProperty("javax.net.ssl.trustAllHosts", "true");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaAiApplication.class, args);
    }

}
