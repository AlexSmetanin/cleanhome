package chpt.cleanhome.repository;

import chpt.cleanhome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
