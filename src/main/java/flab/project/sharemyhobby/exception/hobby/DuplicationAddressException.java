package flab.project.sharemyhobby.exception.hobby;

import flab.project.sharemyhobby.model.address.Address;

import java.text.MessageFormat;

public class DuplicationAddressException extends RuntimeException {

    public DuplicationAddressException(long userId, Address address) {
        super(MessageFormat.format("User id [{0}] has already registered this address : {1}", userId, address));
    }

}
