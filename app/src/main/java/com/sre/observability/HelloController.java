package com.sre.observability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "SRE Observability App Running";
    }

    @GetMapping("/load")
    public String load() {
        // simple load simulation
        try { Thread.sleep(200); } catch (Exception e) {}
        return "Loading The Endpoint";
    }
}
