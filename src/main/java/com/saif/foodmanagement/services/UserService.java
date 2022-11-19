package com.saif.foodmanagement.services;

import com.saif.foodmanagement.dao.UserDao;
import com.saif.foodmanagement.utils.HashGenerationUtil;
import com.saif.foodmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

/**
 * @author saifuzzaman
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    private Optional<User> find(int id) {
        return userDao.findById(id);
    }

    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            user.setPassword(HashGenerationUtil.getSha512(user.getPassword()));
            user.setCreatedOn(Date.from(now()));
        }

        user.setUpdatedOn(Date.from(now()));

        return userDao.saveOrUpdate(user);
    }

    public User getOrCreate(int id) {
        return find(id).orElse(new User());
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public boolean isBlocked(int id) {
        return find(id).get().getIsBlocked();
    }

    public User blockOrUnblock(int id) {
        User user = find(id).get();
        user.setIsBlocked(!user.getIsBlocked());

        return saveOrUpdate(user);
    }

    public boolean isExistsUsername(User user) {
        Optional<User> userObj = findByUsername(user.getUsername());
        return userObj.isPresent() && !user.equals(userObj.get());
    }

    public boolean isExistsEmail(User user) {
        Optional<User> userObj = findByEmail(user.getEmail());
        return userObj.isPresent() && !user.equals(userObj.get());
    }
}
