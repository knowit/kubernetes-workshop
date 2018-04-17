package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@EnableAutoConfiguration
public class Controller {
    @Autowired
    private Environment environment;

    private static final String MYID = UUID.randomUUID().toString();
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/")
    String home() {
        logger.info("Serving root");
        String port = environment.getProperty("local.server.port");
        return "Sample app hello world, id=" + MYID + ".  Running on port " + port;
    }

    @RequestMapping("/test")
    String test() {
        logger.info("Serving test");
        return "TEST!";
    }


    @RequestMapping("/error2")
    String error() {
        logger.info("Serving error");
        return "ERRRRRRRRRRRRORRR!";
    }

}