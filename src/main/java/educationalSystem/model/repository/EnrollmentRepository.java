package educationalSystem.model.repository;

import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.EnrollmentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository implements Repository<Enrollment, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final EnrollmentMapper enrollmentMapper = new EnrollmentMapper();

    public EnrollmentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Enrollment enrollment) throws Exception {
        enrollment.setEnrollmentCode(ConnectionProvider.getProvider().getNextId("enrollment_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Enrollment (enrollment_code, enrollment_status, lesson_code, teacher_code, student_code, payment_code, register_date )"+
                        "values (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, enrollment.getEnrollmentCode());
        preparedStatement.setString(2, enrollment.getEnrollmentStatus().name());
        preparedStatement.setInt(3, enrollment.getLesson().getLessonCode());
        preparedStatement.setInt(4, enrollment.getTeacher().getTeacherCode());
        preparedStatement.setInt(5, enrollment.getStudent().getStudentCode());
        preparedStatement.setInt(6, enrollment.getPayment().getPaymentCode());
        preparedStatement.setDate(7,Date.valueOf(enrollment.getRegisterDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Enrollment enrollment) throws Exception {
        preparedStatement = connection.prepareStatement(
          "update Enrollment set enrollment_status=?, student_code=?, payment_code=? where enrollment_code=?"
        );
        preparedStatement.setString(1, enrollment.getEnrollmentStatus().name());
        preparedStatement.setInt(2, enrollment.getStudent().getStudentCode());
        preparedStatement.setInt(3, enrollment.getPayment().getPaymentCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer enrollmentCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Enrollment where enrollment_code=?"
        );
        preparedStatement.setInt(1, enrollmentCode);
        preparedStatement.execute();
    }

    @Override
    public List<Enrollment> findAll() throws Exception {
        List<Enrollment> enrollmentsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
              "select * from Enrollment order by payment_code, enrollment_status "
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Enrollment enrollment = enrollmentMapper.enrollmentMapper(resultSet);
            enrollmentsList.add(enrollment);
        }
        return enrollmentsList;
    }

    @Override
    public Enrollment findById(Integer enrollmentCode) throws Exception {
        Enrollment enrollment = null;

        preparedStatement = connection.prepareStatement(
                "select * from Enrollment where enrollment_code=?"
        );
        preparedStatement.setInt(1, enrollmentCode);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            enrollment = enrollmentMapper.enrollmentMapper(resultSet);
        }
        return enrollment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
