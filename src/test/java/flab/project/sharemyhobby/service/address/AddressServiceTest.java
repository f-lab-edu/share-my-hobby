package flab.project.sharemyhobby.service.address;

import flab.project.sharemyhobby.api.kakao.KakaoRestApiHelper;
import flab.project.sharemyhobby.exception.address.AddressCountOverException;
import flab.project.sharemyhobby.exception.hobby.DuplicationAddressException;
import flab.project.sharemyhobby.mapper.address.AddressMapper;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private KakaoRestApiHelper kakaoRestApiHelper;

    @Mock
    private AddressMapper addressMapper;

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

    @Test
    @DisplayName("유저의 주소가 이미 모두 등록되어 있으면 AddressCountOverException을 던진다")
    void testThrowAddressCountOverExceptionWhenAllUserAddressesAreRegistered() {
        List<Address> addressList = List.of(
                Address.of("4113554500", "경기 성남시 분당구 정자동", 127.1115403336, 37.361449216955),
                Address.of("1168010800", "서울 강남구 논현동", 127.030154778539, 37.5126451506882));
        Address address = Address.of("4136053000", "경기 남양주시 금곡동", 127.200659122727, 37.6256794631023);

        when(addressMapper.findUserAddressByUserId(1L))
                .thenReturn(addressList);

        assertThatThrownBy(() -> addressService.registerAddress(address, 1L))
            .isInstanceOf(AddressCountOverException.class);
    }

    @Test
    @DisplayName("유저의 같은 주소가 등록되어 있으면 DuplicationAddressException을 던진다")
    void testThrowDuplicationAddressExceptionWhenSameAddressesIsRegistered() {
        Address address = Address.of("4113554500", "경기 성남시 분당구 정자동", 127.1115403336, 37.361449216955);
        doThrow(DuplicationAddressException.class)
                .when(addressMapper)
                .saveUserAddress(1L, address);

        assertThatThrownBy(() -> addressService.registerAddress(address, 1L))
                .isInstanceOf(DuplicationAddressException.class);
    }

}