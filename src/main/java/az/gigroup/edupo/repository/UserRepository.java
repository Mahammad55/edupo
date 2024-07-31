package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findUserByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    List<User> findAll();

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findById(Long id);
}
