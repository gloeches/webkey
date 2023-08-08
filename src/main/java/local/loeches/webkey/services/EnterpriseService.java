package local.loeches.webkey.services;

import jakarta.transaction.Transactional;
import local.loeches.webkey.models.Enterprise;
import local.loeches.webkey.models.Keypass;
import local.loeches.webkey.repository.EnterpriseRepository;
import local.loeches.webkey.repository.KeypassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private KeypassRepository keypassRepository;
    public ResponseEntity<List<Enterprise>> getAllEnterprises(String name){
        List<Enterprise> enterpriseList=new ArrayList<Enterprise>();
        if(name==null)
            enterpriseRepository.findAll().forEach(enterpriseList::add);
        else
            enterpriseRepository.findByNameContaining(name).forEach(enterpriseList::add);
        if(enterpriseList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(enterpriseList,HttpStatus.OK);
    }
    public ResponseEntity<List<Keypass>> getAllKeypassByEnterpriseId(long id){
        List<Keypass> keypasses=  keypassRepository.findByEnterpriseId(id);
        keypasses.forEach(p->p.getEnterprise().getName());


        return new ResponseEntity<>(keypasses,HttpStatus.OK);
    }
    public ResponseEntity<Keypass> createKeypass(long id, Keypass keypassRequest){
        Keypass keypass=enterpriseRepository.findById(id).map(enterprise -> {
            keypassRequest.setEnterprise(enterprise);
            return keypassRepository.save(keypassRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found here"));
        return new ResponseEntity<>(keypass, HttpStatus.CREATED);

    }

}
