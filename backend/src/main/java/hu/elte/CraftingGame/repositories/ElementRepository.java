package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.Element;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<Element, Integer> {
    public Optional<Element> findByElementName(String elementName);
    public Iterable<Element> findAllByFirstParentAndSecondParent(String firstParent, String secondParent);
    public Optional<Element> findByFirstParentAndSecondParent(String firstParent, String secondParent);
}
