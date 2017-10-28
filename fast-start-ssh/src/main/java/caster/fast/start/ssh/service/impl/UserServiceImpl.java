package caster.fast.start.ssh.service.impl;

import caster.fast.start.ssh.dao.UserDao;
import caster.fast.start.ssh.pojo.User;
import caster.fast.start.ssh.service.UserService;

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
