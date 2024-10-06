package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.domain.TestDB;



@Controller
public class DemoController {
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("data", "방가워요");
    return "hello";
  }
  @GetMapping("/about_detailed")
  public String about() {
    return "about_detailed";
  }
  @GetMapping("/index")
  public String index() {
    return "index";
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
  //   TestDB test = testService.findByName("안하서");
  //   model.addAttribute("data4", data);
  //   System.out.println("데이터 출력 디버그: " + test);
  //     return "testdb"();
  // }
  
}
