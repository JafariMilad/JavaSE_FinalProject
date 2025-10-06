package educationalSystem.model.repository;

import educationalSystem.model.entity.Student;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.StudentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final StudentMapper studentMapper =  new StudentMapper();

    public StudentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Student student) throws Exception {
        student.setStudentCode(ConnectionProvider.getProvider().getNextId("student_code"));
        preparedStatement = connection.prepareStatement(
                    "insert into Students (student_code, enrollment_code, project_code, exersise_code, attendance_code, lesson_code)" +
                            " values (?, ?, ?, ?, ?, ?) )"
        );
        preparedStatement.setInt(1, student.getStudentCode());
        preparedStatement.setInt(2,student.getEnrollment().getEnrollmentCode());
        preparedStatement.setInt(3,student.getProject().getProjectCode());
        preparedStatement.setInt(4,student.getExersise().getExersiseCode());
        preparedStatement.setInt(5,student.getAttendance().getAttendanceCode());
        preparedStatement.setInt(6,student.getLesson().getLessonCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit (Student student) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Students set enrollment_code=?, project_code=?, exersise_code=?, attendance_code=?, lesson_code=? where student_code=?"
        );
        preparedStatement.setInt(1, student.getEnrollment().getEnrollmentCode());
        preparedStatement.setInt(2,student.getProject().getProjectCode());
        preparedStatement.setInt(3,student.getExersise().getExersiseCode());
        preparedStatement.setInt(4,student.getAttendance().getAttendanceCode());
        preparedStatement.setInt(5,student.getLesson().getLessonCode());
        preparedStatement.setInt(6,student.getStudentCode());
        preparedStatement.execute();
    }

    @Override
    public void delete (Integer studentCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Students where student_code=?"
        );
        preparedStatement.setInt(1, studentCode);
        preparedStatement.execute();
    }

    @Override
    public List<Student> findAll() throws Exception {
        List<Student> studentList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from Students where student_code=?"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Student student = studentMapper.studentMapper(resultSet);
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public Student findById(Integer studentCode) throws Exception {
        Student student = null;
        preparedStatement = connection.prepareStatement(
                "select * from Students where student_code=?"
        );
        preparedStatement.setInt(1, studentCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            student = studentMapper.studentMapper(resultSet);
        }
        return student;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
