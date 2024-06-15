package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    Optional<Object> findByEmail(String email);
}
