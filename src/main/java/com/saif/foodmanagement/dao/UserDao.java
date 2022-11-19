package com.saif.foodmanagement.dao;

import com.saif.foodmanagement.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.saif.foodmanagement.model.User.*;

/**
 * @author saifuzzaman
 */
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }

        return user;
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public List<User> findAll() {
        return entityManager.createNamedQuery(FIND_ALL_USERS, User.class).getResultList();
    }

    public Optional<User> findByUsername(String username) {
        return entityManager.createNamedQuery(FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return entityManager.createNamedQuery(FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(User.class, id));
    }

    public boolean isExistsUsername(String username) {
        String query = "FROM User WHERE username = :username";

        return !entityManager.createQuery(query)
                .setParameter("username", username)
                .getResultList()
                .isEmpty();
    }

    public boolean isExistsEmail(String email, int id) {
        String query = "FROM User WHERE email = :email AND id != :id";

        return !entityManager.createQuery(query)
                .setParameter("email", email)
                .setParameter("id", id)
                .getResultList()
                .isEmpty();
    }
}
