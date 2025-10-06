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
                    "insert into Students (student_code, enrollment_code, project_code, exersise_code, attendance_code, lesson_code) values (?, ?, ?, ?, ?, ?) )"
        );
    }
}
