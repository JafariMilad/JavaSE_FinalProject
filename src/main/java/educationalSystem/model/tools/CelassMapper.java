package educationalSystem.model.tools;

import educationalSystem.model.entity.Celass;
import educationalSystem.model.service.*;

import java.sql.ResultSet;

public class CelassMapper {
    public Celass  celassMapper(ResultSet resultSet) throws Exception {
        return Celass
                .builder()
                .classCode(resultSet.getInt("class_code"))
                .session(SessionService.getService().findById(resultSet.getInt("session_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .enrollment(EnrollmentService.getService().findById(resultSet.getInt("enrollment_code")))
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .teacher(TeacherService.getService().findById(resultSet.getInt("teacher_code")))
                .build();
    }
}