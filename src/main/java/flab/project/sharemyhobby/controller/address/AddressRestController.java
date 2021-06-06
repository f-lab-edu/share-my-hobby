package flab.project.sharemyhobby.controller.address;

import flab.project.sharemyhobby.annotaion.LoginUserId;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.api.request.address.AddressRequest;
import flab.project.sharemyhobby.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressRestController {

    private final AddressService addressService;

    @GetMapping
    public List<Address> search(@RequestBody AddressRequest addressRequest) {
        return addressService.getAddressList(addressRequest);
    }

    @PostMapping
    public void register(@RequestBody Address address, @LoginUserId long userId) {
        addressService.registerAddress(address, userId);
    }

}
