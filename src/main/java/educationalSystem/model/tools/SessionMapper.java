package educationalSystem.model.tools;

import educationalSystem.model.entity.Session;
import educationalSystem.model.service.*;

import java.sql.ResultSet;

public class SessionMapper {
    public Session sessionMapper(ResultSet resultSet) throws Exception {
        return Session
                .builder()
                .sessionCode(resultSet.getInt("session_code"))
                .celass(CelassService.getService().findById(resultSet.getInt("celass_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .attendance(AttendanceService.getService().findById(resultSet.getInt("attendance_code")))
                .exersise(ExersiseService.getService().findById(resultSet.getInt("exersise_code")))
                .build();
    }
}
