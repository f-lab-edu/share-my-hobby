package flab.project.sharemyhobby.model.address;

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

    public static Address of(String addressCode, String addressName, double x, double y) {
        return new Address(addressCode, addressName, x, y);
    }

}
