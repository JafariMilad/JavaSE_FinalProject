package educationalSystem.model.tools;

import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.entity.enums.EnrollmentStatus;
import educationalSystem.model.service.*;

import java.sql.ResultSet;

public class EnrollmentMapper {
    public Enrollment enrollmentMapper(ResultSet resultSet) throws Exception {
     return Enrollment
             .builder()
             .enrollmentCode(resultSet.getInt("enrollment_code"))
             .enrollmentStatus(EnrollmentStatus.valueOf(resultSet.getString("enrollment_status")))
             .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
             .teacher(TeacherService.getService().findById(resultSet.getInt("teacher_code")))
             .student(StudentService.getService().findById(resultSet.getInt("student_code")))
             .payment(PaymentService.getService().findById(resultSet.getInt("payment_code")))
             .registerDate(resultSet.getDate("register_date").toLocalDate())
             .build();
    }
}