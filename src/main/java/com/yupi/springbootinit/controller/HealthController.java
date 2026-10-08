package com.yupi.springbootinit.controller;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Health endpoint for container platforms. */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public Map<String, String> health() {
        return Collections.singletonMap("status", "ok");
    }
}
