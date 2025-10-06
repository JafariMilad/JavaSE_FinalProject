package educationalSystem.model.repository;

import educationalSystem.model.entity.Exersise;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.ExersiseMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExersiseRepository implements Repository<Exersise, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final ExersiseMapper exersiseMapper = new ExersiseMapper();

    public ExersiseRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Exersise exersise) throws Exception {
        exersise.setExersiseCode(ConnectionProvider.getProvider().getNextId("exercise_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Exersises (exersise_code,exersise_date, student_code,lesson_code,session_code) " +
                        "values (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, exersise.getExersiseCode());
        preparedStatement.setDate(2, Date.valueOf(exersise.getExersiseDate()));
        preparedStatement.setInt(3,exersise.getStudent().getStudentCode());
        preparedStatement.setInt(4,exersise.getLesson().getLessonCode());
        preparedStatement.setInt(5,exersise.getSession().getSessionCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Exersise exersise) throws Exception{
        preparedStatement = connection.prepareStatement(
                "update Exersises set exersise_date=?, student_code=? ,lesson_code=?,session_code=? where exersise_code=?");


        preparedStatement.setDate(1, Date.valueOf(exersise.getExersiseDate()));
        preparedStatement.setInt(2, exersise.getStudent().getStudentCode());
        preparedStatement.setInt(3, exersise.getLesson().getLessonCode());
        preparedStatement.setInt(4, exersise.getSession().getSessionCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer exersiseCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Exersises where exersise_code=?"
        );
        preparedStatement.setInt(1, exersiseCode);
        preparedStatement.execute();
    }

    @Override
    public List<Exersise> findAll() throws Exception {
        List<Exersise> exersiseList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Exersises order by exersise_code, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Exersise exersise = exersiseMapper.exersiseMapper(resultSet);
            exersiseList.add(exersise);
        }
        return exersiseList;
    }

    @Override
    public Exersise findById(Integer exersiseCode) throws Exception {
        Exersise exersise = null;

        preparedStatement = connection.prepareStatement(
                "select * from Exersises where exersise_code=?"
        );
        preparedStatement.setInt(1,exersise.getExersiseCode());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            exersise = exersiseMapper.exersiseMapper(resultSet);
        }
        return exersise;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}