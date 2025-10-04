package educationalSystem.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Attendance {

    private int attendanceId;
    private Student student;
    private Celass celass;
    private Session session;
    private LocalDate attendanceDate;
    private Lesson lesson;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
