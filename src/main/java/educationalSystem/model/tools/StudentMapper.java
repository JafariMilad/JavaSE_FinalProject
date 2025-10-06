package educationalSystem.model.tools;

import educationalSystem.model.entity.Student;
import educationalSystem.model.service.AttendanceService;
import educationalSystem.model.service.ProjectService;
import educationalSystem.model.service.EnrollmentService;
import educationalSystem.model.service.LessonService;


import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;

public class StudentMapper {
    public Student studentMapper (ResultSet resultSet) throws Exception{
        return Student
                .builder()
                .studentCode(resultSet.getInt("student_code"))
                .enrollment(EnrollmentService.getService().findById(resultSet.getInt("enrollment_code")))
                .project(ProjectService.getService().findById(resultSet.getInt("project_code")))
                .exersise(ExecutorService.getService().findById(resultSet.getInt("exersise_code")))
                .attendance(AttendanceService.getService().findById(resultSet.getInt("attendance_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();
    }


}
