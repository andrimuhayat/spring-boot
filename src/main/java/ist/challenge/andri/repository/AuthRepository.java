package ist.challenge.andri.repository;

import ist.challenge.andri.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
}
