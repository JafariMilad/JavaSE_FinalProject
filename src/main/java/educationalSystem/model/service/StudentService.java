package educationalSystem.model.service;

import educationalSystem.model.entity.Student;
import educationalSystem.model.repository.StudentRepository;
import lombok.Getter;

import java.util.List;

public class StudentService implements Service<Student, Integer> {
    @Getter
    private static StudentService service = new StudentService();

    private StudentService(){}

    @Override
    public void save(Student entity) throws Exception {
        try (StudentRepository studentRepository = new StudentRepository()) {
            studentRepository.save(entity);
        }
    }

    @Override
    public void edit(Student entity) throws Exception {
        try (StudentRepository studentRepository = new StudentRepository()) {
            studentRepository.save(entity);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (StudentRepository studentRepository = new StudentRepository()) {
            studentRepository.delete(id);
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        try (StudentRepository studentRepository = new StudentRepository()) {
            return studentRepository.findAll();
        }
    }

    @Override
    public Student findById(Integer id) throws Exception {
        try (StudentRepository studentRepository = new StudentRepository()) {
            return studentRepository.findById(id);
        }
    }
}
