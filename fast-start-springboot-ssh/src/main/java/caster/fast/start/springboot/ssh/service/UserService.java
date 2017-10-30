package caster.fast.start.springboot.ssh.service;

import caster.fast.start.springboot.ssh.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUser();

    public User findUserById(Long id);

}
