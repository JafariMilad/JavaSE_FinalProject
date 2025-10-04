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

public class Session {

    private int sessionCode;
    private Celass celass;
    private Lesson lesson;
    private Attendance attendance;
    private Exersise exersise;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
