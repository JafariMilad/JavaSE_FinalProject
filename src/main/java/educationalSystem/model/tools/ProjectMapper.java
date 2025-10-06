package educationalSystem.model.tools;

import educationalSystem.model.entity.Project;
import educationalSystem.model.entity.enums.ProjectStatus;
import educationalSystem.model.service.LessonService;
import educationalSystem.model.service.SessionService;
import educationalSystem.model.service.StudentService;

import java.sql.ResultSet;

public class ProjectMapper {
    public Project projectMapper(ResultSet resultSet) throws Exception {
        return  Project
                .builder()
                .projectCode(resultSet.getInt("project_code"))
                .projectTitle(resultSet.getString("project_title"))
                .student(StudentService.getService().findById(resultSet.getInt("student_code")))
                .lesson(LessonService.getService().findById(resultSet.getInt("lesson_code")))
                .session(SessionService.getService().findBy(resultSet.getInt("session_code")))
                .status(ProjectStatus.valueOf(resultSet.getString("status")))
                .build();
}
