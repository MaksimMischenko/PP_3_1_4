package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class RoleDaoImp implements RoleDao {

    private final EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ru.kata.spring.boot_security.demo.model.Role findByRole(String role) {
        return entityManager.createQuery("SELECT role FROM Role role where role.role = :role",
                ru.kata.spring.boot_security.demo.model.Role.class).setParameter("role", role).getSingleResult();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}