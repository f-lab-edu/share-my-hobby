package flab.project.sharemyhobby.mapper.address;

import flab.project.sharemyhobby.model.address.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AddressMapper {

    List<Address> findUserAddressByUserId(long userId);

    void saveUserAddress(@Param("userId") long userId, @Param("address") Address address);

}
