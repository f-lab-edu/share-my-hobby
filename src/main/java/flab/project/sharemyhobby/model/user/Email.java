package flab.project.sharemyhobby.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Email {

    private String address;

    @JsonCreator
    public Email(@JsonProperty("address") String address) {
        this.address = address;
    }
}
