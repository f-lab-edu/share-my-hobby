package flab.project.sharemyhobby.model.api.request.hobby;

import flab.project.sharemyhobby.model.hobby.LikeHobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LikeHobbyRequest {

    @Size(min=1, max=5, message = "Please choose more than {min} and less than {max} hobbies you like")
    List<LikeHobby> likeHobbyList;

}
