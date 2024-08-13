package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findByNameAndActive(String name, Integer active);

    boolean existsCustomerByEmailAndActive(String email, Integer active);

    boolean existsByEmailAndActiveAndIdNot(String email, Integer active, Long id);

    Optional<Customer> findByIdAndActive(Long id, Integer active);
}