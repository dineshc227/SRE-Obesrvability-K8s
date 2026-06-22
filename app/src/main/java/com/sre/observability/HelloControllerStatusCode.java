package com.sre.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloControllerStatusCode {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public ResponseEntity<String> home() {
        logger.info("Home endpoint called");
        return ResponseEntity.ok("SRE Observability App Running");
    }

    @GetMapping("/load")
    public ResponseEntity<String> load() {
        long start = System.currentTimeMillis();
        logger.info("Load endpoint triggered");

        try {
            Thread.sleep(200); // simulate latency
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Error");
        }

        long duration = System.currentTimeMillis() - start;
        logger.info("Load endpoint completed in {} ms", duration);

        return ResponseEntity.ok("Loading The Endpoint");
    }

    // ✅ Simulate 400 error
    @GetMapping("/bad")
    public ResponseEntity<String> badRequest() {
        logger.warn("Bad request endpoint hit");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("400 Bad Request - Invalid Input");
    }

    // ✅ Simulate 500 error
    @GetMapping("/error")
    public ResponseEntity<String> serverError() {
        logger.error("Simulated internal server error triggered");

        try {
            int x = 10 / 0; // force exception
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("500 Internal Server Error");
        }

        return ResponseEntity.ok("Should not reach here");
    }
}
