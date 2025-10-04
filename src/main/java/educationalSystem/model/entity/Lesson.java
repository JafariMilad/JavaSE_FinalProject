package educationalSystem.model.entity;

import com.google.gson.Gson;
import educationalSystem.model.entity.enums.LessonStatus;
import educationalSystem.model.entity.enums.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Lesson {
    private int lessonCode;
    private String lessonName;
    private LessonStatus lessonStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private Time time;
    private Teacher teacher;
    private Student student;
    private Celass celass;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
