package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.Element;
import hu.elte.CraftingGame.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findById(Long id);
}
