package com.comcast.registration.dao;

import com.comcast.registration.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
@Repository("userDao")
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public User findById(String id) {
        return entityManager.find(User.class, id);
    }


    public void saveUser(User user) {
        entityManager.persist(user);
    }


    public void updateUser(User user) {
        entityManager.persist(user);
    }


    public void deleteUser(User user) {
        entityManager.remove(user);
    }


    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u ").getResultList();
    }

    public boolean isUserExist(User user) {
        User persistedUser = entityManager.find(User.class, user.getId());
        return persistedUser != null;
    }


}
