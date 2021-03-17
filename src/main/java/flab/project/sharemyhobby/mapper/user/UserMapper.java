package flab.project.sharemyhobby.mapper.user;

import flab.project.sharemyhobby.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    long saveUser(User user);

}
