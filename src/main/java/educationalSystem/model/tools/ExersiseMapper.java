package educationalSystem.model.tools;

import educationalSystem.model.entity.Exersise;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.SessionService;

import java.sql.ResultSet;

public class ExersiseMapper {
    public Exersise exersiseMapper(ResultSet resultSet) throws Exception {
        return  Exersise
                .builder()
                .exersiseCode(resultSet.getInt("exersise_code"))
                .exersiseDate(resultSet.getDate("exersise_date").toLocalDate())
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .session(SessionService.getService().findById(resultSet.getInt("session_code")))
                .student(SessionService.getService().findById(resultSet.getInt("student_code")))
                .build();

    }
