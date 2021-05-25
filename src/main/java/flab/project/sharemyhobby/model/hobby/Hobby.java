package flab.project.sharemyhobby.model.hobby;

import lombok.*;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class Hobby {

    private final int id;

    private final String hobbyName;

    /*  @ConstructorProperties
     *  : 생성자의 파라미터에 해당하는 getter 이름을 지정해주기 위한 자바 빈 애노테이션
     *    Jackson이 JSON 형태의 데이터를 deserialize 할 때 기본 생성자를 사용해 인스턴스를 생성하고
     *    setter로 필드 값을 셋팅하는데, immutable 객체의 경우 setter가 존재하지 않기 때문에
     *    @ConstructorProperties로 호출할 생성자와 파라미터에 해당하는 getter 이름을 직접 지정
     */
    @ConstructorProperties({"id", "hobbyName"})
    public Hobby(int id, String hobbyName) {
        this.id = id;
        this.hobbyName = hobbyName;
    }

}
