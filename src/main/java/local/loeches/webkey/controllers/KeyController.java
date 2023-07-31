package local.loeches.webkey.controllers;

import local.loeches.webkey.models.Enterprise;
import local.loeches.webkey.models.Greeting;
import local.loeches.webkey.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class KeyController {
    @Autowired
    EnterpriseRepository enterpriseRepository;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @PostMapping("/enterprise")
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise enterprise){
        Enterprise _enterprise= enterpriseRepository.save (new Enterprise(enterprise.getName(),enterprise.getProjectLeader()));
        return new ResponseEntity<>(_enterprise, HttpStatus.CREATED);
    }
}
