package hello.Repository;
import java.util.List;

import hello.Domain.Customer;
import hello.Domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>{

    List<Person> findByLastName(String LastName) ;


}
