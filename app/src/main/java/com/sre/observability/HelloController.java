package com.sre.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    // Health Check Endpoint
    @GetMapping("/health")
    public String health() {
        logger.info("Health check endpoint called at {}", LocalDateTime.now());
        return "Application is UP";
    }

    // Home Endpoint
    @GetMapping("/")
    public String home() {
        String requestId = UUID.randomUUID().toString();
        logger.info("Home endpoint hit | requestId={}", requestId);
        return "SRE Observability App Running";
    }

    // Load Simulation Endpoint
    @GetMapping("/load")
    public String load() {
        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        logger.info("Load endpoint started | requestId={}", requestId);

        try {
            Thread.sleep(200); // simulate latency
        } catch (InterruptedException e) {
            logger.error("Error during load simulation | requestId={} | error={}", requestId, e.getMessage());
        }

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Load endpoint completed | requestId={} | duration={}ms", requestId, duration);

        return "Loading The Endpoint";
    }

    // Error Simulation Endpoint
    @GetMapping("/error")
    public String error() {
        String requestId = UUID.randomUUID().toString();
        logger.warn("Error endpoint triggered | requestId={}", requestId);

        try {
            int x = 10 / 0;
        } catch (Exception e) {
            logger.error("Exception occurred | requestId={} | error={}", requestId, e.getMessage());
            return "Error occurred!";
        }

        return "This won't execute";
    }

    // Custom Message Endpoint
    @GetMapping("/message")
    public String message(@RequestParam(defaultValue = "User") String name) {
        String requestId = UUID.randomUUID().toString();
        logger.info("Message endpoint called | requestId={} | name={}", requestId, name);

        return "Hello " + name + "!";
    }
}
