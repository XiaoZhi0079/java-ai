package com.example.javaai.service.impl;


import com.example.javaai.CustomTextSplitter;
import com.example.javaai.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final RedisVectorStore redisVectorStore;

    @Override
    public void loadText(Resource resource, String fileName) {
        // 加载读取文档
        TextReader textReader = new TextReader(resource);
        textReader.getCustomMetadata().put("fileName", fileName);
        List<Document> documents = textReader.get();
        // 切割文档
        CustomTextSplitter customTextSplitter = new CustomTextSplitter();
        List<Document> list = customTextSplitter.apply(documents);
        // 向量存储
        redisVectorStore.add(list);
    }

    @Override
    public List<Document> doSearch(String question) {
        return List.of();
    }

}
