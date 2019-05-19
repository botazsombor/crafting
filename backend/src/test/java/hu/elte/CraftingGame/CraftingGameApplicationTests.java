package hu.elte.CraftingGame;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
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
	
    //True tests
    //
    @Test
    public void whenFindByName_thenReturnElement() {
        // given
    	
        Element element = new Element("child",1,2, null,null);
        entityManager.persist(element);
        entityManager.flush();
     
        // when
        Optional<Element> found = elementRepository.findByElementName(element.getElementName());
        
        // then
        Assert.assertEquals(found.get(), element);
    }
    
    @Test
    public void whenFindByFirstParentAndSecondParent_thenReturnElement() {
    	Element element = new Element("child",2,3, null,null); //2-3 mert az 1-2-nek már van gyereke, ezért nem egy optionalelem lesz a result hanem egy iterable és ez hiba
        entityManager.persist(element);
        entityManager.flush();

        //dupla csekk meg a fordított verziónál is! így írtuk meg nincs mese!
        Optional<Element> found = elementRepository.findByFirstParentAndSecondParent(2, 3);
        if(!found.isPresent()) {
            found = elementRepository.findByFirstParentAndSecondParent(3, 2);
        }

        Assert.assertEquals(found.get(), element);
    }
    
    

    @Test
    public void whenFindBySecondParentAndFirstParent_thenReturnElement() {
    	Element element = new Element("child",2,3, null,null);
        entityManager.persist(element);
        entityManager.flush();
        
        Optional<Element> found = elementRepository.findByFirstParentAndSecondParent(2, 3);
        if(!found.isPresent()) {
            found = elementRepository.findByFirstParentAndSecondParent(3, 2);
        }
        
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
    //Failure tests
    @Test
    public void whenNotFindByName_thenReturnElement() {
        // given
    	
        Element element = new Element("child",1,2, null,null);
        Element element2 = new Element("child2",3,4, null,null);
        entityManager.persist(element);
        entityManager.persist(element2);
        entityManager.flush();
     
        // when
        Optional<Element> found = elementRepository.findByElementName(element.getElementName());
        
        // then
        Assert.assertNotEquals(found.get(),element2);
    }
    @Test
    public void whenFindNotByFirstParentAndSecondParent_thenReturnElement() {
    	Element element = new Element("child",2,3, null,null);
    	Element element2 = new Element("child",3,4, null,null);
        entityManager.persist(element);
        entityManager.persist(element2);
        entityManager.flush();

        Optional<Element> found = elementRepository.findByFirstParentAndSecondParent(1, 2);
        
        Assert.assertNotEquals(found.get(), element2);
    }
    @Test
    public void whenNotFindByUsername_thenReturnUser() {
    	User user = new User("username", "password", Role.ROLE_USER, null);
    	User user2 = new User("username2", "password", Role.ROLE_USER, null);
        entityManager.persist(user);
        entityManager.flush();
        
        Optional<User> found = userRepository.findByUsername("username");
        
        Assert.assertNotEquals(found.get(), user2);
    }
}
