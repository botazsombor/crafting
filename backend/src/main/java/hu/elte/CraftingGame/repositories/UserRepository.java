package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {
    Optional<User> findByUsername(String username);
}
