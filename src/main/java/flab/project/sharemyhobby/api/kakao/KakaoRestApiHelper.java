package flab.project.sharemyhobby.api.kakao;

import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import flab.project.sharemyhobby.model.api.response.address.AddressInfo;
import flab.project.sharemyhobby.model.api.response.address.KaKaoApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class KakaoRestApiHelper {

    @Value("${kakao.restapi.key}")
    private String restApiKey;

    private static final String API_SERVER_HOST = "https://dapi.kakao.com";
    private static final String SEARCH_ADDRESS_URL = "/v2/local/search/address.json";

    public List<Address> getAddressByTownName(AddressRequest addressRequest) {
        URI url = UriComponentsBuilder.fromHttpUrl(API_SERVER_HOST + SEARCH_ADDRESS_URL)
                .queryParam("query", addressRequest.getTownName())
                .queryParam("analyze_type", addressRequest.getAnalyzeType())
                .queryParam("page", addressRequest.getPage())
                .queryParam("size", addressRequest.getSize())
                .build().toUri();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10 * 1000);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + restApiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<KaKaoApiResponse> response = restTemplate.exchange(request, KaKaoApiResponse.class);

        return extractAddressList(response);
    }

    private List<Address> extractAddressList(ResponseEntity<KaKaoApiResponse> response) {
        List<AddressInfo> addressInfoList = response.getBody().getAddressInfoList();
        List<Address> addressList = new ArrayList<>();

        addressInfoList.forEach(addressInfo -> {
            String hCode = addressInfo.getAddress().get("h_code").toString();
            String bCode = addressInfo.getAddress().get("b_code").toString();
            String addressCode = hCode.isEmpty() ? bCode : hCode;
            String addressName = addressInfo.getAddress().get("address_name").toString();
            Double axisX = Double.parseDouble(addressInfo.getAddress().get("x").toString()) ;
            Double axisY = Double.parseDouble(addressInfo.getAddress().get("y").toString());
            addressList.add(Address.of(addressCode, addressName, axisX, axisY));
        });

        return addressList;
    }

}
