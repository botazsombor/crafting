package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User>{
    Optional<User> findByUsername(String username);
}
