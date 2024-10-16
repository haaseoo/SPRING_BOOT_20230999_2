package com.example.demo.controller;

import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가
import com.example.demo.model.domain.TestDB;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  @Autowired
  TestService testService;

  @GetMapping("/hello") // get 전송방식
  public String hello(Model model) {
    model.addAttribute("data", "방가워요"); // model 설정
    return "hello"; // hello.html 연결
  }
  @GetMapping("/about_detailed")
  public String about() {
    return "about_detailed";
  }

  @GetMapping("/index")
  public String index() {
    return "index";  // templates/index.html 파일을 렌더링
  }

  @GetMapping("/test1")
  public String thymeleaf_test1(Model model) {
    model.addAttribute("data1", "<h2>반갑습니다</h2>");
    model.addAttribute("data2", "태그의 속성 값");
    model.addAttribute("link", 01);
    model.addAttribute("name", "안하서");
    model.addAttribute("para1", "001");
    model.addAttribute("para2", "002");
    return "thymeleaf_test1";
  }

  // @GetMapping("/testdb")
  // public String getAllTestDBs(Model model) {
  //   Testdb test = testService.findByName("홍길동");
  //   model.addAttribute("data4", test);
  //   System.out.println("데이터 출력 디버그 : " + test);
  //   return "testdb";
  // }
  
  @GetMapping("/testdb")
  public String getAllUsers(Model model) {
      List<TestDB> users = testService.findAll(); // 모든 데이터 조회
      model.addAttribute("users", users);  // 모델에 데이터 추가
      return "testdb"; // 템플릿 파일명
  }

}

