package hu.elte.CraftingGame.repositories;

import hu.elte.CraftingGame.entities.Element;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<Element, Integer> {
    public Optional<Element> findByElementName(String elementName);
    public Iterable<Element> findAllByFirstParentAndSecondParent(Integer firstParent, Integer secondParent);
    public Optional<Element> findByFirstParentAndSecondParent(Integer firstParent, Integer secondParent);
}
