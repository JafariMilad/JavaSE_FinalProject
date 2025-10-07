package educationalSystem.model.tools;

import educationalSystem.model.entity.Payment;
import educationalSystem.model.entity.enums.PaymentingStatus;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.StudentService;

import java.sql.ResultSet;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws Exception{
        return Payment
                .builder()
                .paymentCode(resultSet.getInt("payment_code"))
                .paymentingStatus(PaymentingStatus.valueOf(resultSet.getString("paymenting_status")))
                .paymentDate(resultSet.getDate("payment_date").toLocalDate())
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();
    }
}
