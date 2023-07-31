package local.loeches.webkey.repository;

import local.loeches.webkey.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    List<Enterprise> findByName(String name);

}
