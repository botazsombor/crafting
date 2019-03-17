package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseRepository<T extends BaseEntity> extends CrudRepository<T , Long> {
    List<T> findAll();
    Optional<T> findById(Long id);
}