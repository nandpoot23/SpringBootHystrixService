package com.example.demo;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author mlahariya
 * @version 1.0, Aug 2017
 */

@Service
public class GreetingService {

    private final RestTemplate restTemplate;

    public GreetingService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String greetingTime() {

        URI uri = URI.create("http://localhost:6070/multipleCustomer/v1/welcome");
        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Cloud Native Java (Ohh Really)";
    }

}
