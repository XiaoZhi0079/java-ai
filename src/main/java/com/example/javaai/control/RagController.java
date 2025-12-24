package com.example.javaai.control;

import com.example.javaai.service.DocumentService;
import com.example.javaai.utils.LeeResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/rag")
public class RagController {

    @Resource
    private DocumentService documentService;

    @PostMapping("/upload")
    public LeeResult upload(@RequestParam("file") MultipartFile file) {
        documentService.loadText(file.getResource(),file.getOriginalFilename());
        return LeeResult.ok();
    }

}
