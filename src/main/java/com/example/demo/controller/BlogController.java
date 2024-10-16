package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.domain.Article;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService; // 서비스 계층 의존성 주입

    @GetMapping("/article_list") // 게시판 링크 지정
    public String articleList(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트 조회
        model.addAttribute("articles", list); // 모델에 게시글 목록 추가
        return "article_list"; // article_list.html 템플릿 반환
    }
}
