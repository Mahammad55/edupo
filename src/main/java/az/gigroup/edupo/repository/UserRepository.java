package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByEmail(String email);
}
