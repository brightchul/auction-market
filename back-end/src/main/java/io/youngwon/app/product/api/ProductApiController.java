package io.youngwon.app.product.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiController {

    @GetMapping("/cpu-test")
    public void test(){
        System.out.println("hi");
    }
}
