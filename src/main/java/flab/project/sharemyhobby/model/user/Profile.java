package flab.project.sharemyhobby.model.user;

import lombok.*;

@ToString
@Getter
@Builder(toBuilder=true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Profile {

    private final Long id;

    @NonNull
    private final Long userId;

    private String profileImageUrl;

    private String statusMessage;

}
