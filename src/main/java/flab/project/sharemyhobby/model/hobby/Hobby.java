package flab.project.sharemyhobby.model.hobby;

import lombok.*;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class Hobby {

    private final int id;

    private final String hobbyName;

    @ConstructorProperties({"id", "hobbyName"})
    public Hobby(int id, String hobbyName) {
        this.id = id;
        this.hobbyName = hobbyName;
    }

}
