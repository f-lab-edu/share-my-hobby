package flab.project.sharemyhobby.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.matches;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Getter
public class Email {

    private String address;

    @JsonCreator
    public Email(@JsonProperty("address") String address) {
        checkArgument(isNotEmpty(address), "Email address must be provided.");
        checkArgument(checkAddress(address), "This is an invalid email address : " + address);
        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", address);
    }

}
