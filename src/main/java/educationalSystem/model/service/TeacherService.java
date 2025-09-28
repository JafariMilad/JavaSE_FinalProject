package educationalSystem.model.service;

import educationalSystem.model.entity.Teacher;
import educationalSystem.model.repository.TeacherRepository;
import lombok.Getter;

import java.util.List;

public class TeacherService implements Service<Teacher, Integer> {

    @Getter
    private static TeacherService service = new TeacherService();

    private TeacherService() {
    }


    @Override
    public void save(Teacher teacher) throws Exception {
        try (TeacherRepository teacherRepository = new TeacherRepository()) {
            teacherRepository.save(teacher);
        }
    }

    @Override
    public void edit(Teacher teacher) throws Exception {
        try (TeacherRepository teacherRepository = new TeacherRepository()) {
            teacherRepository.edit(teacher);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (TeacherRepository teacherRepository = new TeacherRepository()) {
            teacherRepository.delete(id);
        }
    }

    @Override
    public List<Teacher> findAll() throws Exception {
        try (TeacherRepository teacherRepository = new TeacherRepository()) {
            return teacherRepository.findAll();
        }
    }

    @Override
    public Teacher findById(Integer id) throws Exception {
        try (TeacherRepository teacherRepository = new TeacherRepository()) {
            return teacherRepository.findById(id);
        }
    }

}
