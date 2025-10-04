package educationalSystem.model.entity;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Celass {
    private int classCode;
    private Session session;
    private Lesson lesson;
    private Enrollment enrollment;
    private Student student;
    private Teacher teacher;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
