package educationalSystem.model.repository;

import educationalSystem.model.entity.Payment;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.PaymentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment, Integer>, AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Payment payment) throws Exception {
        payment.setPaymentCode(ConnectionProvider.getProvider().getNextId("payment_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Payments (payment_code, paymenting_status, payment_date, student_code, lesson_code)" +
                        "values (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getPaymentCode());
        preparedStatement.setString(2,payment.getPaymentingStatus().name());
        preparedStatement.setDate(3,Date.valueOf(payment.getPaymentDate()));
        preparedStatement.setInt(4, payment.getStudent().getStudentCode());
        preparedStatement.setInt(5, payment.getLesson().getLessonCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Payments set paymenting_status=?, payment_date=?, student_code=?, lesson_code=? where payment_code=?"
        );
        preparedStatement.setString(1,payment.getPaymentingStatus().name());
        preparedStatement.setDate(2,Date.valueOf(payment.getPaymentDate()));
        preparedStatement.setInt(3, payment.getStudent().getStudentCode());
        preparedStatement.setInt(4, payment.getLesson().getLessonCode());
        preparedStatement.execute();

    }

    @Override
    public void delete(Integer paymentCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Payments where payment_code=?"
        );
        preparedStatement.setInt(1, paymentCode);
        preparedStatement.execute();
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from Payments order by paymenting_status, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Payment payment = paymentMapper.paymentMapper(resultSet);
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment findById(Integer paymentCode) throws Exception {
        Payment payment = null;

        preparedStatement = connection.prepareStatement(
                "select * from Payments where payment_code=?"
        );
        preparedStatement.setInt(1, paymentCode);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            payment = paymentMapper.paymentMapper(resultSet);
        }
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
