package educationalSystem.model.repository;

import educationalSystem.model.entity.Celass;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.CelassMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelassRepository implements Repository<Celass, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final CelassMapper celassMapper = new CelassMapper();

    public CelassRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Celass celass) throws Exception{
        celass.setClassCode(ConnectionProvider.getProvider().getNextId("celass_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Celasses (celass_code, session_id, lesson_code, enrollment_code, student_code, teacher_code) " +
                        "values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, celass.getClassCode());
        preparedStatement.setInt(2,celass.getSession().getSessionCode());
        preparedStatement.setInt(3,celass.getLesson().getLessonCode());
        preparedStatement.setInt(4,celass.getEnrollment().getEnrollmentCode());
        preparedStatement.setInt(5,celass.getStudent().getStudentCode());
        preparedStatement.setInt(6,celass.getTeacher().getTeacherCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Celass celass) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Celasses set session_id=?, lesson_code=? where celass_code=?"
        );
        preparedStatement.setInt(1, celass.getSession().getSessionCode());
        preparedStatement.setInt(2, celass.getLesson().getLessonCode());
        preparedStatement.setInt(3, celass.getClassCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer celassCode) throws Exception {
     preparedStatement = connection.prepareStatement(
                "delete from Celasses where celass_code=?");
        preparedStatement.setInt(1, celassCode);
        preparedStatement.execute();
    }

    @Override
    public List<Celass> findAll() throws Exception {
        List<Celass> celassList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from Celasses order by lesson_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Celass celass = celassMapper.celassMapper(resultSet);
            celassList.add(celass);
        }
        return celassList;
    }

    @Override
    public Celass findById(Integer celassCode) throws Exception {
        Celass celass = null;

        preparedStatement = connection.prepareStatement(
                "select * from Celasses where celass_code=?"
        );
        preparedStatement.setInt(1, celassCode);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            celass = celassMapper.celassMapper(resultSet);
        }
        return celass;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}