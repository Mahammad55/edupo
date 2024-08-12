package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameAndActive(String name, Integer active);

    boolean existsCustomerByEmailAndActive(String email, Integer active);

    List<Customer> findAllByActive(Integer active);

    Optional<Customer> findByIdAndActive(Long id, Integer active);
}