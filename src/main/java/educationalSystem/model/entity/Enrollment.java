package educationalSystem.model.entity;

import com.google.gson.Gson;

import educationalSystem.model.entity.enums.EnrollmentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import educationalSystem.model.entity.enums.ClassStatus;
import java.time.LocalDate;


@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Enrollment {
    private int enrollmentCode;
    private int classCode;
    private String paymentId;
    private EnrollmentStatus  enrollmentStatus;
    private Lesson lesson;
    private Teacher teacher;
    private Student student;
    private Payment payment;
    private LocalDate registerDate;
    private ClassStatus classStatus;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
