package caster.fast.start.ssm.service;

import caster.fast.start.ssm.pojo.User;

import java.util.List;

public interface UserService {

    public User getUserById(Long id);

    public List<User> getAllUser();

}
