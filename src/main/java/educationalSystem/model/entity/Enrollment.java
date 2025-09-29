package educationalSystem.model.entity;

import com.google.gson.Gson;

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
    private int lessonCode;
    private int classCode;
    private String paymentId;
    private LocalDate registerDate;
    private ClassStatus classStatus;
}
