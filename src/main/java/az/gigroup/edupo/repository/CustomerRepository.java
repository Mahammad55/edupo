package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    boolean existsCustomerByEmailAndActive(String email, Integer active);

    boolean existsByEmailAndActiveAndIdNot(String email, Integer active, Long id);

    Optional<Customer> findByIdAndActive(Long id, Integer active);
}