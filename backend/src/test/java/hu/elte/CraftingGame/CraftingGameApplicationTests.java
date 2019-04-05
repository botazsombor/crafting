package hu.elte.CraftingGame;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import hu.elte.CraftingGame.entities.Element;
import hu.elte.CraftingGame.entities.User;
import hu.elte.CraftingGame.entities.User.Role;
import hu.elte.CraftingGame.repositories.ElementRepository;
import hu.elte.CraftingGame.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CraftingGameApplicationTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ElementRepository elementRepository;
    @Autowired
    private UserRepository userRepository;
	
    @Test
    public void whenFindByName_thenReturnElement() {
        // given
    	
        Element element = new Element("child","firstparent","secondparent", null);
        entityManager.persist(element);
        entityManager.flush();
     
        // when
        Optional<Element> found = elementRepository.findByElementName(element.getElementName());
        
        // then
        Assert.assertEquals(found.get(), element);
    }
    
    @Test
    public void whenFindByFirstParentAndSecondParent_thenReturnElement() {
    	Element element = new Element("child","firstparent","secondparent", null);
        entityManager.persist(element);
        entityManager.flush();
        
        Optional<Element> found = elementRepository.findByFirstParentAndSecondParent("firstparent", "secondparent");
        
        Assert.assertEquals(found.get(), element);
    }
    
    
    //still failure
    @Test
    public void whenFindBySecondParentAndFirstParent_thenReturnElement() {
    	Element element = new Element("child","firstparent","secondparent", null);
        entityManager.persist(element);
        entityManager.flush();
        
        Optional<Element> found = elementRepository.findByFirstParentAndSecondParent("secondparent", "firstparent");
        
        Assert.assertEquals(found.get(), element);
    }
    
    @Test
    public void whenFindByUsername_thenReturnUser() {
    	User user = new User("username", "password", Role.ROLE_USER, null);
        entityManager.persist(user);
        entityManager.flush();
        
        Optional<User> found = userRepository.findByUsername("username");
        
        Assert.assertEquals(found.get(), user);
    }
    

}
