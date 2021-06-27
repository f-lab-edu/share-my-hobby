package flab.project.sharemyhobby.service.address;

import flab.project.sharemyhobby.api.kakao.KakaoRestApiHelper;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService {

    private final KakaoRestApiHelper kakaoRestApiHelper;

    public List<Address> getAddressList(AddressRequest addressRequest) {
        return kakaoRestApiHelper.getAddressByTownName(addressRequest);
    }

}
