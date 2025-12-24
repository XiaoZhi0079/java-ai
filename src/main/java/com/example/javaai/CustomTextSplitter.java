package com.example.javaai;

import org.springframework.ai.transformer.splitter.TextSplitter;
import java.util.List;

public class CustomTextSplitter extends TextSplitter {
    @Override
    protected List<String> splitText(String text) {
        return List.of(split(text));
    }


    public String[] split(String text) {
        // 按“空行（一个或多个换行）”进行分割
        return text.split(" *\\R *\\R\\S*");
    }

}
