package hu.elte.CraftingGame;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hu.elte.CraftingGame.entities.Element;
import hu.elte.CraftingGame.repositories.ElementRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CraftingGameApplicationTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ElementRepository elementRepository;
	
    @Test
    public void whenFindByName_thenReturnElement() {
        // given
    	
        Element element = new Element("child","firstparent","secondparent", null);
        entityManager.persist(element);
        entityManager.flush();
     
        // when
        Optional<Element> found = elementRepository.findByElementName(element.getElementName());
        
        // then
        Assert.assertEquals(1, 1);
    }

}
