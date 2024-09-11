package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DemoController {
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("data", "방가워요");
    return "hello";
  }
}
