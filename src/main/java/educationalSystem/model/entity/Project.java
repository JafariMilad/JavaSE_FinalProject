package educationalSystem.model.entity;

import com.google.gson.Gson;
import educationalSystem.model.entity.enums.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Project {

    private int projectCode;
    private String projectTitle;
    private Lesson lesson;
    private Student student;
    private ProjectStatus projectStatus;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
