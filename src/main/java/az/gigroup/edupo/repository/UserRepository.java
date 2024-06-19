package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
