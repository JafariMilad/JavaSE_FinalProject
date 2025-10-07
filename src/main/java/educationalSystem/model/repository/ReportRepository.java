package educationalSystem.model.repository;

import educationalSystem.model.entity.Report;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.ReportMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository implements Repository<Report, Integer>, AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final ReportMapper reportMapper = new ReportMapper();

    public ReportRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Report report) throws Exception {
        report.setReportCode(ConnectionProvider.getProvider().getNextId("report_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Reports (report_code, report_date, student_code, lesson_code)" +
                        "values (?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, report.getReportCode());
        preparedStatement.setDate(2,Date.valueOf(report.getReportDate()));
        preparedStatement.setInt(3,report.getStudent().getStudentCode());
        preparedStatement.setInt(4,report.getLesson().getLessonCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Report report) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Reports set report_date=?, student_code=?, lesson_code=? where report_code=?"
        );
        preparedStatement.setDate(1, Date.valueOf(report.getReportDate()));
        preparedStatement.setInt(2, report.getStudent().getStudentCode());
        preparedStatement.setInt(3, report.getLesson().getLessonCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer reportCode) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Reports where report_code=?"
        );
        preparedStatement.setInt(1, reportCode);
        preparedStatement.execute();
    }

    @Override
    public List<Report> findAll() throws Exception {
        List<Report> reportList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Reports order by report_date, student_code"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Report report = reportMapper.reportMapper(resultSet);
            reportList.add(report);
        }
        return reportList;
    }

    @Override
    public Report findById(Integer reportCode) throws Exception {
        Report report = null;

        preparedStatement = connection.prepareStatement(
                "select * from Reports where report_code=?"
        );
        preparedStatement.setInt(1, reportCode);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            report = reportMapper.reportMapper(resultSet);
        }
        return report;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
