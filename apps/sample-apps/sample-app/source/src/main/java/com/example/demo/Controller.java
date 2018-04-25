package com.example.demo;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        return "Sample app hello world, id=" + MYID + ".  Running on port " + port + " in namespace " + System.getenv("MY_POD_NAMESPACE");
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

    @RequestMapping("/healthy")
    ResponseEntity health() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/nonhealthy")
    ResponseEntity badHealth() {
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping("/secret")
    ResponseEntity<String> secret() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("/secrets/supersecret.yml")));
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Could not read the secret supersecret!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/configmap")
    ResponseEntity<String> configMap() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("/config/greatestconfig.yml")));
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Could not read the greatest config!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/version")
    ResponseEntity<String> version() {
        String version = System.getenv("MY_VERSION");
        return new ResponseEntity<>("Version: " + version, HttpStatus.OK);
    }

    @RequestMapping("/callother")
    ResponseEntity<String> callEndpoint() {
        String url = System.getenv("MY_OTHER_ENDPOINT_ADDRESS");
        try {
            Response response = Request.Get(url).execute();
            return new ResponseEntity<>(EntityUtils.toString(response.returnResponse().getEntity()), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}