package educationalSystem.model.tools;

import educationalSystem.model.entity.Student;
import educationalSystem.model.service.EnrollmentService;

import java.sql.ResultSet;

public class StudentMapper {
    public Student studentMapper (ResultSet resultSet) throws Exception{
        return Student
                .builder()
                .studentCode(resultSet.getInt("student_code"))
                .build();
    }


}
