package flab.project.sharemyhobby.exception.address;

import flab.project.sharemyhobby.model.address.Address;

import java.text.MessageFormat;
import java.util.List;

public class AddressCountOverException extends RuntimeException {

    public AddressCountOverException(long  userId, List<Address> addresses) {
        super(MessageFormat.format("User id [{0}] has already registered maximun number of addresses : {1}", userId, addresses));
    }

}
