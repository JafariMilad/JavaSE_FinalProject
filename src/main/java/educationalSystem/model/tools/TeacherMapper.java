package educationalSystem.model.tools;

import educationalSystem.model.entity.Teacher;
import educationalSystem.model.service.LessonService;

import java.sql.ResultSet;

public class TeacherMapper {
    public Teacher teacherMapper(ResultSet resultSet) throws Exception {
        return  Teacher
                .builder()
                .id(resultSet.getInt("id"))
                .teacherCode(resultSet.getInt("teacher_code"))
                .userId(resultSet.getInt("user_id"))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();

    }

}
