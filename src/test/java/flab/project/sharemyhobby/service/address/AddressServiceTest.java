package flab.project.sharemyhobby.service.address;

import flab.project.sharemyhobby.api.kakao.KakaoRestApiHelper;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private KakaoRestApiHelper kakaoRestApiHelper;

    @Test
    @DisplayName("동네 이름을 검색하면 해당하는 주소 목록을 리턴한다")
    void testReturnAddressListWhenSearchTownName() {
        AddressRequest addressRequest = new AddressRequest("정자동");
        List<Address> addressList = List.of(
                        Address.of("4113554500", "경기 성남시 분당구 정자동", 127.1115403336, 37.361449216955),
                        Address.of("4111113000", "경기 수원시 장안구 정자동", 126.990841281045, 37.3017558325298));

        when(kakaoRestApiHelper.getAddressByTownName(addressRequest))
                .thenReturn(addressList);

        List<Address> results = addressService.getAddressList(addressRequest);
        assertThat(results).extracting("addressName").contains("경기 성남시 분당구 정자동");
    }

}