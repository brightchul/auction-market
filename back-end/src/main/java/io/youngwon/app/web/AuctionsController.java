package io.youngwon.app.web;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuctionsController {

    @GetMapping("/test")
    public String index(){
        return "index";
    }

}
