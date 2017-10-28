package caster.fast.start.ssm.service.impl;

import caster.fast.start.ssm.dao.UserDao;
import caster.fast.start.ssm.pojo.User;
import caster.fast.start.ssm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.queryUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.queryAllUser();
    }

}
