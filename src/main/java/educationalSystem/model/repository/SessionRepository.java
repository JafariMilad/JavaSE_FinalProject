package educationalSystem.model.repository;

import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.entity.Session;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.SessionMapper;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository implements Repository<Session, Integer>, AutoCloseable  {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final SessionMapper sessionMapper = new SessionMapper();

    public SessionRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Session session) throws Exception {
        session.setSessionCode(ConnectionProvider.getProvider().getNextId("session_seq"));

        preparedStatement = connection.prepareStatement("INSERT INTO sessions (session_code, celass_code, lesson_code, attendance_code, exersise_code)" +
                " VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, session.getSessionCode());
        preparedStatement.setInt(2,session.getCelass().getClassCode());
        preparedStatement.setInt(3,session.getLesson().getLessonCode());
        preparedStatement.setInt(4,session.getAttendance().getAttendanceCode());
        preparedStatement.setInt(5,session.getExersise().getExersiseCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Session session) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Sessions set celass_code=?, lesson_code=?, attendance_code=?, exersise_code=? where session_code=? "
        );
        preparedStatement.setInt(1, session.getCelass().getClassCode());
        preparedStatement.setInt(2, session.getLesson().getLessonCode());
        preparedStatement.setInt(3, session.getAttendance().getAttendanceCode());
        preparedStatement.setInt(4, session.getExersise().getExersiseCode());
        preparedStatement.setInt(5, session.getSessionCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer sessionCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from sessions where session_code=?"
        );
        preparedStatement.setInt(1, sessionCode);
        preparedStatement.execute();
    }

    @Override
    public List<Session> findAll() throws Exception {
        List<Session> sessionList = new ArrayList<>();
        preparedStatement  = connection.prepareStatement(
                "select * from sessions order by session_code, lesson_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Session session = sessionMapper.sessionMapper(resultSet);
            sessionList.add(session);
        }
        return sessionList;
    }

    @Override
    public Session findById(Integer sessionCode) throws Exception {
        Session session = null;

        preparedStatement = connection.prepareStatement(
                "select * from sessions where session_code=?"
        );
        preparedStatement.setInt(1, sessionCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            session = sessionMapper.sessionMapper(resultSet);
        }
        return session;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
