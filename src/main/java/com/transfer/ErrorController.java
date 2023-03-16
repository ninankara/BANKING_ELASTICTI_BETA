package com.transfer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ErrorController{

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("hello","Welcome to COP Website Beta");
        return "/home";
    }
}
