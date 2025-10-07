package educationalSystem.model.tools;

import educationalSystem.model.entity.Report;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.StudentService;

import java.sql.ResultSet;

public class ReportMapper {
    public Report reportMapper(ResultSet resultSet) throws Exception {
        return Report
                .builder()
                .reportCode(resultSet.getInt("report_code"))
                .reportDate(resultSet.getDate("report_date").toLocalDate())
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .build();
    }
}
