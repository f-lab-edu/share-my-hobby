package flab.project.sharemyhobby.model.api.response.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.HashMap;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class AddressInfo {

    private HashMap<String, Object> address;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("address_type")
    private String addressType;

    @JsonProperty("road_address")
    private HashMap<String, Object> roadAddress;

    private Double x;

    private Double y;

}
