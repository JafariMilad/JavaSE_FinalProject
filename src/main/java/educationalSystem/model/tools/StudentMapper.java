package educationalSystem.model.tools;

import educationalSystem.model.entity.Student;
import educationalSystem.model.service.*;


import java.sql.ResultSet;


public class StudentMapper {
    public Student studentMapper (ResultSet resultSet) throws Exception{
        return Student
                .builder()
                .studentCode(resultSet.getInt("student_code"))
                .enrollment(EnrollmentService.getService().findById(resultSet.getInt("enrollment_code")))
                .project(ProjectService.getService().findById(resultSet.getInt("project_code")))
                .exersise(ExersiseService.getService().findById(resultSet.getInt("exersise_code")))
                .attendance(AttendanceService.getService().findById(resultSet.getInt("attendance_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();
    }
}
