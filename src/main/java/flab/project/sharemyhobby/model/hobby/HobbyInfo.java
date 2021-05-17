package flab.project.sharemyhobby.model.hobby;

import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class HobbyInfo {

    private final Integer categoryId;

    private String categoryName;

    private List<Hobby> hobbies;

}
