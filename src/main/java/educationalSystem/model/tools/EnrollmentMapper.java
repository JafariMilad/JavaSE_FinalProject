package educationalSystem.model.tools;

import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.entity.enums.LessonStatus;
import educationalSystem.model.entity.enums.EnrollmentStatus;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.TeacherService;

import java.sql.ResultSet;

public class EnrollmentMapper {
    public Enrollment enrollmentMapper(ResultSet resultSet) throws Exception {
     return Enrollment
             .builder()
             .enrollmentCode(resultSet.getInt("enrollment_code"))
             .classCode(resultSet.getInt("class_code"))
             .paymentId(resultSet.getString("payment_id"))
             .enrollmentStatus(EnrollmentStatus.valueOf(resultSet.getString("enrollment_status")))
             .lesson(LessonService.getService().findById(resultSet.getInt("lesson_id")))
             .teacher(TeacherService.getService().findById(resultSet.getInt("teacher_id")))
             .student(StudentService.getService().findById(resultSet.getInt("student_id")))
             .payment(PaymentService.getService().findById(resultSet.getInt("payment_code")))
             .registerDate(resultSet.getDate("register_date").toLocalDate())
             .classStatus(LessonStatus.valueOf(resultSet.getString("class_status")))
             .build();
    }
}
