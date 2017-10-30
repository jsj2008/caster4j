package caster.fast.start.springboot.ssm.dao;

import caster.fast.start.springboot.ssm.pojo.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAllUser();

    public User findUserByName(String name);

}
