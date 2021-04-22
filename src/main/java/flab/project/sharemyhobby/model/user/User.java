package flab.project.sharemyhobby.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flab.project.sharemyhobby.model.address.Address;
import flab.project.sharemyhobby.model.address.Location;
import lombok.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
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

    public void loginPostProcess() {
        lastLoginAt = now();
    }

    @Builder(toBuilder=true)
    public User(Long id, @NonNull Email email, @NonNull String nickname, @NonNull String password, @NonNull Status status, Profile profile, Location location, Address address, LocalDateTime lastLoginAt, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
        this.profile = profile;
        this.location = location;
        this.address = address;
        this.lastLoginAt = lastLoginAt;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Builder
    public User(Long id, @NonNull Email email, LocalDateTime createAt) {
        this.id = id;
        this.email = email;
        this.createAt = createAt;
    }

}
