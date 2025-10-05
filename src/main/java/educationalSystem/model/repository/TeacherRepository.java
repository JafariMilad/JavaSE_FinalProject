package educationalSystem.model.repository;

import educationalSystem.model.entity.Teacher;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.TeacherMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements Repository<Teacher, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final TeacherMapper teacherMapper = new TeacherMapper();

    public TeacherRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Teacher teacher) throws Exception {
        teacher.setTeacherCode(ConnectionProvider.getProvider().getNextId("teacher_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Teachers (teacher_code,lesson_code) values (?,?)"
        );
        preparedStatement.setInt(1, teacher.getTeacherCode());
        preparedStatement.setInt(2, teacher.getLesson().getLessonCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Teacher teacher) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Teachers set lesson_code=? where teacher_code=?");

        preparedStatement.setInt(1, teacher.getLesson().getLessonCode());
        preparedStatement.setInt(2, teacher.getTeacherCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer teacherCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Teachers where teacher_code=?"
        );
        preparedStatement.setInt(1, teacherCode);
        preparedStatement.execute();
    }

    @Override
    public List<Teacher> findAll() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Teachers order by teacher_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Teacher teacher = teacherMapper.teacherMapper(resultSet);
            teacherList.add(teacher);
        }
        return teacherList;
    }

    @Override
    public Teacher findById(Integer lessonCode) throws Exception {
        Teacher teacher = null;

        preparedStatement = connection.prepareStatement(
                "select * from Teachers where lesson_code=?"
        );
        preparedStatement.setInt(1, teacher.getLesson().getLessonCode());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            teacher = teacherMapper.teacherMapper(resultSet);
        }
        return teacher;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}