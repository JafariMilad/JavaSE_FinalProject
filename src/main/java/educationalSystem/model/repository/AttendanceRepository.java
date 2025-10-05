package educationalSystem.model.repository;

import educationalSystem.model.entity.Attendance;
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
        attendance.setAttendanceCode(ConnectionProvider.getProvider().getNextId("attendance_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into ATTENDANCES (attendance_code,student_code, attendance_status,celass_code,session_Code,attendance_date, lesson_code) " +
                        "values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, attendance.getAttendanceCode());
        preparedStatement.setInt(2, attendance.getStudent().getStudentCode());
        preparedStatement.setString(3,attendance.getAttendanceStatus().name());
        preparedStatement.setInt(4,attendance.getCelass().getClassCode());
        preparedStatement.setInt(5,attendance.getSession().getSessionCode());
        preparedStatement.setDate(6, Date.valueOf(attendance.getAttendanceDate()));
        preparedStatement.setInt(7, attendance.getLesson().getLessonCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Attendance attendance) throws Exception{
        preparedStatement = connection.prepareStatement(
                "update ATTENDANCES set student_code=?, session_code=? ,attendance_status=?,attendance_date=? where attendance_code=?");


        preparedStatement.setInt(1, attendance.getStudent().getStudentCode());
        preparedStatement.setInt(2, attendance.getSession().getSessionCode());
        preparedStatement.setString(3, attendance.getAttendanceStatus().name());
        preparedStatement.setDate(4, Date.valueOf(attendance.getAttendanceDate()));
        preparedStatement.setInt(5, attendance.getAttendanceCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer attendanceCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from ATTENDANCES where attendance_code=?"
        );
        preparedStatement.setInt(1, attendanceCode);
        preparedStatement.execute();
    }

    @Override
    public List<Attendance> findAll() throws Exception {
        List<Attendance> attendanceList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from ATTENDANCES order by attendance_code, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Attendance attendance = attendanceMapper.attendanceMapper(resultSet);
            attendanceList.add(attendance);
        }
        return attendanceList;
    }

    @Override
    public Attendance findById(Integer attendanceCode) throws Exception {
        Attendance attendance = null;

        preparedStatement = connection.prepareStatement(
                "select * from ATTENDANCES where attendance_code=?"
        );
        preparedStatement.setInt(1,attendance.getAttendanceCode());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            attendance = attendanceMapper.attendanceMapper(resultSet);
        }
        return attendance;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
