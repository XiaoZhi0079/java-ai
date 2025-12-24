package com.example.javaai.repository;

import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ChatHistoryRepositoryImpl implements ChatHistoryRepository{

    @Autowired
    private ChatMemory chatMemory;

    private final Map<String,List<String>> chathistory=new HashMap<>();
    @Override
    public void save(String chatId, String type) {
//     if (!chathistory.containsKey(type)){
//         chathistory.put(type, new ArrayList<>());
//     }
//     List<String> chatids=chathistory.get(type);
     List<String> chatids=chathistory.computeIfAbsent(type, k -> new ArrayList<>());


     if (chatids.contains(chatId)){
         return;
     }
     chatids.add(chatId);
    }

    @Override
    public List<String> get(String type) {
//        if (!chathistory.containsKey(type)){
//            return new ArrayList<>();
//        }
//        List<String> chatids=chathistory.get(type);
//        return chatids;
        return chathistory.getOrDefault(type, new ArrayList<>());
    }

    @Override
    public void delete(String type) {

    }

    @Override
    public void deleteAll(String type) {

    }

    @Override
    public List<Message> getbyid(String chatId) {
        return chatMemory.get(chatId);
    }


}
