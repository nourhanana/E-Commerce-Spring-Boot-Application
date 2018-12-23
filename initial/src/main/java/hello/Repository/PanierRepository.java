package hello.Repository;

import hello.Domain.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PanierRepository extends JpaRepository<Panier,Long> {
}
