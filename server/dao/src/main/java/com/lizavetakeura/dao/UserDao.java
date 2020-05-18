package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.IUserDao;
import com.lizavetakeura.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    private UserDao() {
        super(User.class);
    }
}
