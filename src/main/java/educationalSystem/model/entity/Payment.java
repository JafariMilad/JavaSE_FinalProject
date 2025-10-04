package educationalSystem.model.entity;

import com.google.gson.Gson;
import educationalSystem.model.entity.enums.PaymentingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment {

    private int paymentCode;
    private PaymentingStatus paymentingStatus;
    private LocalDate paymentDate;
    private Student  student;
    private Lesson lesson;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
