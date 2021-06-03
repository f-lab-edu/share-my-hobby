package flab.project.sharemyhobby.model.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class Address {

    private final String addressCode;

    private final String addressName;

    private final double axisX;

    private final double axisY;

    /*  @JsonCreator & @JsonProperty
     *  : Jackson이 JSON을 역직렬화 할 때 기본적으로 기본 생성자를 사용해 인스턴스를 생성한 후
     *    setter를 사용하여 각 필드들을 셋팅하는데, immutable 객체를 생상하는 경우 이 방식이 불가능하기 때문에
     *    @JsonCreator로 생성자 혹은 스태틱 팩토리 메소드를 지정해 주고, @JsonProperty로 필드 값을 주입해 주어야 함
     */
    @JsonCreator
    public static Address of(@JsonProperty("addressCode") String addressCode,
                             @JsonProperty("addressName") String addressName,
                             @JsonProperty("axisX") double x,
                             @JsonProperty("axisY") double y) {
        return new Address(addressCode, addressName, x, y);
    }

}
