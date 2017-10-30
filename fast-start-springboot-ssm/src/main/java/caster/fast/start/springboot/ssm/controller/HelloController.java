package caster.fast.start.springboot.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String main(@RequestParam(value="k", required=false, defaultValue="Hello, Spring boot for SSM!") String key, Model model) {
        model.addAttribute("key", key);
        return "index";
    }

    @RequestMapping("/hello")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="SSM-BOOT") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
