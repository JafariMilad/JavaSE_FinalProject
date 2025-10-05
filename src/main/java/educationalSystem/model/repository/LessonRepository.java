package educationalSystem.model.repository;

import educationalSystem.model.entity.Lesson;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.LessonMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements Repository<Lesson, Integer>, AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final LessonMapper lessonMapper = new LessonMapper();

    public LessonRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Lesson lesson) throws Exception {
        lesson.setLessonCode(ConnectionProvider.getProvider().getNextId("lesson_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into Lessons (lesson_code, lesson_name, lesson_status, start_date, end_date, lesson_price, lesson_time, lesson_teacher, student_code, celass_code)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, lesson.getLessonCode());
        preparedStatement.setString(2, lesson.getLessonName());
        preparedStatement.setString(3, lesson.getLessonStatus().name());
        preparedStatement.setDate(4, Date.valueOf(lesson.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(lesson.getEndDate()));
        preparedStatement.setInt(6,lesson.getPrice());
        preparedStatement.setString(7,lesson.getTime().name());
        preparedStatement.setInt(8,lesson.getTeacher().getTeacherCode());
        preparedStatement.setInt(9,lesson.getStudent().getStudentCode());
        preparedStatement.setInt(10,lesson.getCelass().getClassCode());
        preparedStatement.execute();
    }

    @Override
    public void edit(Lesson lesson) throws Exception {
        preparedStatement = connection.prepareStatement(
//                ?
                "update Lessons set lesson_name=?, lesson_status=?, lesson_time=?,lesson_teacher=? where lesson_code=?"
        );
        preparedStatement.setString(1, lesson.getLessonName());
        preparedStatement.setString(2, lesson.getLessonStatus().name());
        preparedStatement.setString(3, lesson.getTime().name());
        preparedStatement.setInt(4, lesson.getTeacher().getTeacherCode());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer lessonCode) throws Exception {
        preparedStatement = connection.prepareStatement(
//                ?
                "delete from Lessons where lesson_code=?"
        );
        preparedStatement.setInt(1, lessonCode);
        preparedStatement.execute();
    }

    @Override
    public List<Lesson> findAll() throws Exception {
        List<Lesson> lessonList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Lessons order by class_code, lesson_teacher"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Lesson lesson = lessonMapper.lessonMapper(resultSet);
            lessonList.add(lesson);
        }
        return lessonList;
    }

    @Override
    public Lesson findById(Integer lessonCode) throws Exception {
        Lesson lesson = null;

        preparedStatement = connection.prepareStatement(
//                ?
                "select * from Lessons where lesson_code = ?"
        );
        preparedStatement.setInt(1, lessonCode);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            lesson = lessonMapper.lessonMapper(resultSet);
        }
        return lesson;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
