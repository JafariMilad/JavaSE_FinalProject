package educationalSystem.model.tools;

import educationalSystem.model.entity.Teacher;

import java.sql.ResultSet;

public class TeacherMapper {
    public Teacher teacherMapper(ResultSet resultSet) throws Exception {
        return  Teacher
                .builder()
                .id(resultSet.getInt("id"))
                .teacherCode(resultSet.getInt("teacher_code"))
                .userId(resultSet.getString("user_id"))
                .lesson(resultSet.getString("lesson"))
                .build();
    }

}
