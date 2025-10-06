package educationalSystem.model.tools;

import educationalSystem.model.entity.Lesson;
import educationalSystem.model.entity.enums.LessonStatus;
import educationalSystem.model.entity.enums.Time;
import educationalSystem.model.service.*;
import java.sql.ResultSet;

public class LessonMapper {
    public Lesson lessonMapper(ResultSet resultSet) throws Exception {
        return Lesson
                .builder()
                .lessonCode(resultSet.getInt("lesson_code"))
                .lessonName(resultSet.getString("lesson_name"))
                .lessonStatus(LessonStatus.valueOf(resultSet.getString("lesson_status")))
                .startDate(resultSet.getDate("start_date").toLocalDate())
                .endDate(resultSet.getDate("end_date").toLocalDate())
                .price(resultSet.getInt("lesson_price"))
                .time(Time.valueOf(resultSet.getString("lesson_time")))
                .teacher(TeacherService.getService().findById(resultSet.getInt("lesson_teacher")))
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .celass(CelassService.getService().findById(resultSet.getInt("celass_code")))
                .build();
    }
}
