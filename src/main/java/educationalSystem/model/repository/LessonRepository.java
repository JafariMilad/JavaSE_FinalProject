package educationalSystem.model.repository;

import educationalSystem.model.entity.Lesson;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.LessonMapper;
import educationalSystem.model.tools.TeacherMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
                "insert into Lessons (lesson_code, class_code, lesson_name, lesson_status, lesson_date, lesson_teacher) values (?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, lesson.getLessonCode());
        preparedStatement.setInt(2, lesson.getClassCode());
        preparedStatement.setString(3, lesson.getLessonName());
        preparedStatement.setString(4, lesson.getLessonStatus());
        preparedStatement.setDate(5, Date.valueOf(lesson.getLessonDate()));
        preparedStatement.setInt(6, lesson.getLessonTeacher());
        preparedStatement.execute();
    }

    @Override
    public void edit(Lesson lesson) throws Exception {
        preparedStatement = connection.prepareStatement(
//                ?
                "update Lessons set lesson_code=?, lesson_name=?, lesson_status=?, lesson_date=?,lesson_teacher=? where id=?"
        );
        preparedStatement.setInt(1, lesson.getLessonCode());
        preparedStatement.setString(2, lesson.getLessonName());
        preparedStatement.setString(3, lesson.getLessonStatus());
        preparedStatement.setDate(4, Date.valueOf(lesson.getLessonDate()));
        preparedStatement.setInt(5, lesson.getLessonTeacher());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
//                ?
                "delete from Lessons where id = ?"
        );
        preparedStatement.setInt(1, id);
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
    public Lesson findById(Integer id) throws Exception {
        Lesson lesson = null;

        preparedStatement = connection.prepareStatement(
//                ?
                "select * from Lessons where id = ?"
        );
        preparedStatement.setInt(1, id);
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
