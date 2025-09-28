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
        teacher.setId(ConnectionProvider.getProvider().getNextId("teacher_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Teachers (id,teacher_code, user_id,lesson) values (?,?, ?,?)"
        );
        preparedStatement.setInt(1, teacher.getId());
        preparedStatement.setInt(2, teacher.getTeacherCode());
        preparedStatement.setString(3, teacher.getUserId());
        preparedStatement.setString(4, teacher.getLesson());
        preparedStatement.execute();
    }

        @Override
        public void edit(Teacher teacher) throws Exception{
            preparedStatement = connection.prepareStatement(
                    "update Teachers set teacher_code=?, user_id=?, lesson=? where id=?");

            preparedStatement.setInt(1, teacher.getTeacherCode());
            preparedStatement.setString(2, teacher.getUserId());
            preparedStatement.setString(3, teacher.getLesson());
            preparedStatement.execute();
        }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Teachers where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Teacher> findAll() throws Exception {
        List<Teacher> teacherList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Teachers order by id, teacher_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Teacher teacher = teacherMapper.teacherMapper(resultSet);
            teacherList.add(teacher);
        }
        return teacherList;
    }

    @Override
    public Teacher findById(Integer id) throws Exception {
        Teacher teacher = null;

        preparedStatement = connection.prepareStatement(
                "select * from Teachers where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            teacher = teacherMapper.teacherMapper(resultSet);
        }
        return teacher;
    }
    public int getNextId() throws Exception {
        ResultSet resultSet = connection.prepareStatement("SELECT teacher_seq.nextval AS NEXTID FROM DUAL").executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTID");
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
    }


