package local.loeches.webkey.repository;

import local.loeches.webkey.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    List<Enterprise> findByName(String name);
    List<Enterprise>findByNameContaining(String name);

 //   @Override
//    Optional<Enterprise> findById(Long aLong);



}
