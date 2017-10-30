package caster.fast.start.springboot.ssh.dao;

import caster.fast.start.springboot.ssh.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="tbb_user")
@Qualifier("userDAO")
public interface UserDAO extends JpaRepository<User, Long> {

}
