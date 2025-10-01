package educationalSystem.model.repository;

import educationalSystem.model.entity.Attendance;
import educationalSystem.model.entity.Teacher;
import educationalSystem.model.tools.AttendanceMapper;
import educationalSystem.model.tools.ConnectionProvider;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository implements Repository<Attendance, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final AttendanceMapper attendanceMapper = new AttendanceMapper();

    public AttendanceRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Attendance attendance) throws Exception {
        attendance.setAttendanceId(ConnectionProvider.getProvider().getNextId("attendance_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into ATTENDANCE (attendance_id,student_code, session_id,kelass_code,status,attendance_date) values (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, attendance.getAttendanceId());
        preparedStatement.setInt(2, attendance.getStudent().getstudentCode());
        preparedStatement.setInt(3, attendance.getSession().getsessionId());
        preparedStatement.setInt(4, attendance.getKelass().getkelassCode());
        preparedStatement.setString(4, attendance.getStatus());
        preparedStatement.setDate(4, Date.valueOf(attendance.getAttendanceDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Attendance attendance) throws Exception{
        preparedStatement = connection.prepareStatement(
                "update ATTENDANCE set student_code=?, session_id=?, kelass_code=? ,status=?,attendance_date=? where attendance_id=?");


        preparedStatement.setInt(1, attendance.getStudent().getstudentCode());
        preparedStatement.setInt(2, attendance.getSession().getsessionId());
        preparedStatement.setInt(3, attendance.getKelass().getkelassCode());
        preparedStatement.setString(4, attendance.getStatus());
        preparedStatement.setDate(5, Date.valueOf(attendance.getAttendanceDate()));
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer attendance_id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from ATTENDANCE where attendance_id=?"
        );
        preparedStatement.setInt(1, attendance_id);
        preparedStatement.execute();
    }

    @Override
    public List<Attendance> findAll() throws Exception {
        List<Attendance> attendanceList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from ATTENDANCE order by attendance_id, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Attendance attendance = attendanceMapper.attendanceMapper(resultSet);
            attendanceList.add(attendance);
        }
        return attendanceList;
    }

    @Override
    public Attendance findById(Integer attendance_id) throws Exception {
        Attendance attendance = null;

        preparedStatement = connection.prepareStatement(
                "select * from ATTENDANCE where attendance_id=?"
        );
        preparedStatement.setInt(1,attendance.getAttendanceId());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            attendance = attendanceMapper.attendanceMapper(resultSet);
        }
        return attendance;
    }
    public int getNextId() throws Exception {
        ResultSet resultSet = connection.prepareStatement("SELECT attendance_seq.nextval AS NEXTID FROM DUAL").executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTID");
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
