package flab.project.sharemyhobby.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.address.Location;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder=true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of={"id", "email"})
@ToString
public class User {

    private final Long id;

    @NonNull
    private final Email email;

    @NonNull
    private String nickname;

    @NonNull
    @JsonIgnore
    private String password;

    @NonNull
    private Status status;

    private Profile profile;

    private Location location;

    private Address address;

    private LocalDateTime lastLoginAt;

    private final LocalDateTime createAt;

    private LocalDateTime updateAt;

}
