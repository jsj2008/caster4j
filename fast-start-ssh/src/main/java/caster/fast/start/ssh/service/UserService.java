package caster.fast.start.ssh.service;

import caster.fast.start.ssh.pojo.User;

import java.util.List;

public interface UserService {

    public User getUserById(Long id);

    public List<User> getAllUser();

}
