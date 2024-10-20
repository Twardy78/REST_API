package com.crud.tasks.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("equal1", "2 * 2 = ");
        model.put("equal2", "2 * 2 + 2 = ");
        model.put("equal3", "2 - 2 * 2 = ");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
