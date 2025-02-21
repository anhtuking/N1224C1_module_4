package com.techzen.academy_n1224c1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/greeting")   // API, endpoint
    public String greeting(@RequestParam(defaultValue = "Anh Tu") String name, @RequestParam(defaultValue = "Viet Nam") String address) {
        return "Hello " + name + " " + address + "!";
    }
}
//http://localhost:8080/hello
