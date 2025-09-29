package educationalSystem.model.service;

import educationalSystem.model.entity.Lesson;
import educationalSystem.model.repository.LessonRepository;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class LessonService implements Service<Lesson, Integer> {

    @Getter
    private static LessonService service =  new LessonService();

    private LessonService() {
    }

    @Override
    public void save(Lesson lesson) throws Exception {
        try (LessonRepository lessonRepository = new LessonRepository()) {
            lessonRepository.save(lesson);
        }
    }

    @Override
    public void edit(Lesson lesson) throws Exception {
        try (LessonRepository lessonRepository = new LessonRepository()) {
            lessonRepository.edit(lesson);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (LessonRepository lessonRepository = new LessonRepository()) {
            lessonRepository.delete(id);
        }

    }

    @Override
    public List<Lesson> findAll() throws Exception {
        try (LessonRepository lessonRepository = new LessonRepository()) {
            return lessonRepository.findAll();
        }
    }

    @Override
    public Lesson findById(Integer id) throws Exception {
        try (LessonRepository lessonRepository = new LessonRepository()) {
            return lessonRepository.findById(id);
        }
    }
}
