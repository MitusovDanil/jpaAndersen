package jpaTest;

import entity.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserTest {

    private EntityManager em;

    @BeforeEach
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    public void close() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getEntityManagerFactory().close();
        em.close();
    }

    @Test
    public void insertUserIntoDataBase() {
        User user = new User();
        user.setName("Danil");
        user.setEmail("111@111");
        user.setPassword("123");
        em.persist(user);
    }

    @Test
    public void findConcreteUser() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@test");
        user.setPassword("test");
        em.persist(user);

        User user1 = em.find(User.class, 1L);
        assertEquals(user, user1);
    }
}
