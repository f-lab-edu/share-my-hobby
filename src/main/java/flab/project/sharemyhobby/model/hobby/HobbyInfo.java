package flab.project.sharemyhobby.model.hobby;

import lombok.*;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class HobbyInfo {

    private final Integer categoryId;

    private String categoryName;

    private List<Hobby> hobbies;

    @ConstructorProperties({"categoryId", "categoryName", "hobbies"})
    public HobbyInfo(Integer categoryId, String categoryName, List<Hobby> hobbies) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.hobbies = hobbies;
    }

}
