package flab.project.sharemyhobby.model.api.request.address;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AddressRequest {

    private String townName;

    private final String analyzeType = "exact";

    private final int page = 1;

    private final int size = 10;

}
