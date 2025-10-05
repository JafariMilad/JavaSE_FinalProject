package educationalSystem.model.tools;

import educationalSystem.model.entity.Attendance;
import educationalSystem.model.entity.enums.AttendanceStatus;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.StudentService;
import educationalSystem.model.service.CelassService;
import educationalSystem.model.service.SessionService;

import java.sql.ResultSet;
import java.time.LocalDate;

import static educationalSystem.model.entity.Teacher.*;

public class AttendanceMapper {
    public Attendance attendanceMapper(ResultSet resultSet) throws Exception {
        return Attendance
                .builder()
                .attendanceCode(resultSet.getInt("attendance_code"))
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .attendanceStatus(AttendanceStatus.valueOf(resultSet.getString("attendance_status")))
                .celass(CelassService.getService().findById(resultSet.getInt("celass_code")))
                .session(SessionService.getService().findById(resultSet.getInt("session_Code")))
                .attendanceDate(resultSet.getDate("attendance_date").toLocalDate())
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();
    }
}

