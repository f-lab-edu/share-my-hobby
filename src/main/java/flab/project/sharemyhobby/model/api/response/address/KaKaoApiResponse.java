package flab.project.sharemyhobby.model.api.response.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class KaKaoApiResponse {

    private HashMap<String, Object> meta;

    @JsonProperty("documents")
    private List<AddressInfo> addressInfoList;

}
