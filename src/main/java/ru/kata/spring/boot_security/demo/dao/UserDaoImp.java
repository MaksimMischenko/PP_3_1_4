package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleDao roleDao;

    public UserDaoImp(PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode("1"));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode("1"));
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("SELECT user FROM User user where user.name = :username",
                User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public List<User> getListUsers() {

        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

}