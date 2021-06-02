package flab.project.sharemyhobby.model.hobby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LikeHobby {

    private final int hobbyId;

    private final int categoryId;

}
