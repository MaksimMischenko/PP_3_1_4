package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.email = :username",
                User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public boolean isUserExist(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        if (((org.hibernate.query.Query) query).list().isEmpty()) {
            return false;
        }
        return true;
    }

}