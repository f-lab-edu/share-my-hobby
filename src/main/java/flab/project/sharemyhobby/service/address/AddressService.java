package flab.project.sharemyhobby.service.address;

import flab.project.sharemyhobby.api.kakao.KakaoRestApiHelper;
import flab.project.sharemyhobby.exception.address.AddressCountOverException;
import flab.project.sharemyhobby.exception.hobby.DuplicationAddressException;
import flab.project.sharemyhobby.mapper.address.AddressMapper;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService {

    private final KakaoRestApiHelper kakaoRestApiHelper;

    private final AddressMapper addressMapper;

    private static final int MAX_USER_ADDRESS_CNT = 2;


    public List<Address> getAddressList(AddressRequest addressRequest) {
        return kakaoRestApiHelper.getAddressByTownName(addressRequest);
    }

    @Transactional
    public void registerAddress(Address address, long userId) {
        List<Address> userAddressList = addressMapper.findUserAddressByUserId(userId);
        if (userAddressList.size() >= MAX_USER_ADDRESS_CNT)
            throw new AddressCountOverException(userId, userAddressList);

        try {
            addressMapper.saveUserAddress(userId, address);
        } catch (DuplicateKeyException e) {
            throw new DuplicationAddressException(userId, address);
        }
    }

}
