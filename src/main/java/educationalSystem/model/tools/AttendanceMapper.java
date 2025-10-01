package educationalSystem.model.tools;

import educationalSystem.model.entity.Attendance;
import educationalSystem.model.service.LessonService;

import java.sql.ResultSet;
import java.time.LocalDate;

import static educationalSystem.model.entity.Teacher.*;

public class AttendanceMapper {
    public Attendance attendanceMapper(ResultSet resultSet) throws Exception {
        return Attendance
                .builder()
                .attendanceId(resultSet.getInt("attendance_id"))
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .Kelass(KelassService.getService().findById(resultSet.getInt("kelass_code")))
                .session(SessionService.getService().findById(resultSet.getInt("session_id")))
                .status(resultSet.getString("status"))
                .attendanceDate(resultSet.getDate("attendance_date").toLocalDate())
                .build();

    }
}

