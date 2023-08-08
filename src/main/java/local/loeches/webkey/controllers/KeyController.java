package local.loeches.webkey.controllers;

import local.loeches.webkey.models.Enterprise;
import local.loeches.webkey.models.Greeting;
import local.loeches.webkey.models.Keypass;
import local.loeches.webkey.repository.EnterpriseRepository;
import local.loeches.webkey.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class KeyController {
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    private EnterpriseService enterpriseService;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/enterprises")
    public ResponseEntity<List<Enterprise>> getAllEnterprises(@RequestParam (required=false)  String name){
                return enterpriseService.getAllEnterprises(name);
    }
    @GetMapping("/enterprises/{enterpriseId}/keypass")
    public ResponseEntity<List<Keypass>> getAllkeypassByEnterpriseID(@PathVariable(value="enterpriseId") long enterpriseId){
        return enterpriseService.getAllKeypassByEnterpriseId(enterpriseId);
    }
    @PostMapping("/enterprise")
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise enterprise){
        Enterprise _enterprise= enterpriseRepository.save (new Enterprise(enterprise.getName(),enterprise.getProjectLeader()));
        return new ResponseEntity<>(_enterprise, HttpStatus.CREATED);
    }

    @PostMapping("/enterprises/{enterpriseId}/keypass")
    public ResponseEntity<Keypass>  createKeypass(@PathVariable(value="enterpriseId") Long enterpriseId, @RequestBody Keypass keypassRequest){
        return enterpriseService.createKeypass(enterpriseId,keypassRequest);
    }

}
