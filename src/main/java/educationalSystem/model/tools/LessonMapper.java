package educationalSystem.model.tools;

import educationalSystem.model.entity.Lesson;

import java.sql.ResultSet;

public class LessonMapper {
    public Lesson lessonMapper(ResultSet resultSet) throws Exception {
        return Lesson
                .builder()
                .lessonCode(resultSet.getInt("lesson_code"))
                .classCode(resultSet.getInt("class_code"))
                .lessonName(resultSet.getString("lesson_name"))
                .lessonStatus(resultSet.getString("lesson_status"))
                .lessonDate(resultSet.getDate("lesson_date").toLocalDate())
                .lessonTeacher(resultSet.getInt("lesson_teacher"))
                .build();
    }
}
