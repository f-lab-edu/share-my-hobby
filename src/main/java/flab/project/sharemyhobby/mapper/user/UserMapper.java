package flab.project.sharemyhobby.mapper.user;

import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {

    long saveUser(User user);

    void updateUser(User user);

    Optional<User> findByEmail(Email email);

}
