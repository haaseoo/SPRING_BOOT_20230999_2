package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.domain.Article;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
@Configuration
@ControllerAdvice

public class BlogController {
    @Autowired
    BlogService blogService; // 서비스 계층 의존성 주입

    // 게시글 조회
    @GetMapping("/article_list") // 게시판 링크 지정
    public String articleList(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트 조회
        model.addAttribute("articles", list); // 모델에 게시글 목록 추가
        return "article_list"; // article_list.html 템플릿 반환
    }

    @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    public String article_edit(Model model, @PathVariable Long id) {
        Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
        if (list.isPresent()) {
            model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
        } else {
        // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결(이름 수정됨)
        }
    return "article_edit"; // .HTML 연결
    }

    // 수정
    @PutMapping("/api/article_edit/{id}")
        public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
            blogService.update(id, request);
            return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }

    //삭제
    @DeleteMapping("/api/article_delete/{id}")
        public String deleteArticle(@PathVariable Long id) {
            blogService.delete(id);
            return "redirect:/article_list";
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

     // IllegalArgumentException 발생 시 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page/article_error"; // 오류 페이지로 이동
    }

    // MethodArgumentTypeMismatchException 발생 시 처리 (잘못된 타입의 PathVariable 처리)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "잘못된 요청입니다. ID는 숫자여야 합니다.");
        return "error_page/article_error2"; // 오류 페이지로 이동
    }


}
