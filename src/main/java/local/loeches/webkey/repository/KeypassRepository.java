package local.loeches.webkey.repository;

import local.loeches.webkey.models.Keypass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeypassRepository extends JpaRepository<Keypass, Long> {
    List<Keypass> findByEnterpriseId(Long postId);
}
