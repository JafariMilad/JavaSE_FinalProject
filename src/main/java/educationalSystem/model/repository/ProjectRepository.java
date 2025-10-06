package educationalSystem.model.repository;

import educationalSystem.model.entity.Project;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.ProjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements Repository<Project, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final ProjectMapper projectMapper = new ProjectMapper();

    public ProjectRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Project project) throws Exception {
        project.setProjectCode(ConnectionProvider.getProvider().getNextId("project_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into PROJECTS (project_code,project_title,student_code, lesson_code,session_Code, status) " +
                        "values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, project.getProjectCode());
        preparedStatement.setString(2, project.getProjectTitle());
        preparedStatement.setInt(3,project.getStudent().getStudentCode());
        preparedStatement.setInt(4,project.getLesson().getLessonCode());
        preparedStatement.setInt(5,project.getSession().getSessionCode());
        preparedStatement.setString(6, project.getStatus().name());
        preparedStatement.execute();
    }

    @Override
    public void edit(Project project) throws Exception{
        preparedStatement = connection.prepareStatement(
                "update PROJECTS set student_code=?, session_code=? ,attendance_status=?,attendance_date=? where attendance_code=?");


        preparedStatement.setInt(1, project.getProjectCode());
        preparedStatement.setString(2, project.getProjectTitle());
        preparedStatement.setInt(3, project.getStudent().getStudentCode());
        preparedStatement.setInt(4, project.getLesson().getLessonCode());
        preparedStatement.setInt(5, project.getSession().getSessionCode());
        preparedStatement.setString(5, project.getStatus().name());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer projectCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from PROJECTS where project_code=?"
        );
        preparedStatement.setInt(1, projectCode);
        preparedStatement.execute();
    }

    @Override
    public List<Project> findAll() throws Exception {
        List<Project> projectList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from PROJECTS order by project_code, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Project project = projectMapper.projectMapper(resultSet);
            projectList.add(project);
        }
        return projectList;
    }

    @Override
    public Project findById(Integer projectCode) throws Exception {
        Project project = null;

        preparedStatement = connection.prepareStatement(
                "select * from PROJECTS where project_code=?"
        );
        preparedStatement.setInt(1,project.getProjectCode());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            project = projectMapper.projectMapper(resultSet);
        }
        return project;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
