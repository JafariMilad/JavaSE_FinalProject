import educationalSystem.model.entity.Lesson;

import java.time.LocalDate;

public class LessonTest {
    public static void main(String[] args) {
        Lesson lesson1 =
                Lesson
                        .builder()
                        .lessonCode(1)
                        .classCode(123)
                        .lessonName("JavaSE")
                        .lessonStatus("learn java programming language")
                        .lessonDate(LocalDate.of(1986, 12, 1))
                        .lessonTeacher(2121)
                        .build();

        Lesson lesson2 =
                Lesson
                        .builder()
                        .lessonCode(2)
                        .classCode(456)
                        .lessonName("JavaEE")
                        .lessonStatus("learn enterprise java programming language")
                        .lessonDate(LocalDate.of(1988, 11, 2))
                        .lessonTeacher(1212)
                        .build();

    }
}
