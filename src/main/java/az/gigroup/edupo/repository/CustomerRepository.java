package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = "courses")
    List<Customer> findByName(String name);

    boolean existsCustomerByEmail(String email);
}