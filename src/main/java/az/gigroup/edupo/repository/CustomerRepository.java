package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    boolean existsCustomerByEmail(String email);

    @EntityGraph(attributePaths = "courses")
    List<Customer> findAll();

    @EntityGraph(attributePaths = "courses")
    Optional<Customer> findById(Long id);
}