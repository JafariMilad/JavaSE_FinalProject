package educationalSystem.model.service;

import educationalSystem.model.entity.Enrollment;
import educationalSystem.model.repository.EnrollmentRepository;
import lombok.Getter;

import java.util.List;

public class EnrollmentService implements Service<Enrollment, Integer> {
    @Getter
    private static EnrollmentService service = new EnrollmentService();

    private EnrollmentService(){}

    @Override
    public void save(Enrollment enrollment) throws Exception {
        try (EnrollmentRepository enrollmentRepository = new EnrollmentRepository()){
            enrollmentRepository.save(enrollment);
        }
    }

    @Override
    public void edit(Enrollment enrollment) throws Exception {
        try (EnrollmentRepository enrollmentRepository = new EnrollmentRepository()){
            enrollmentRepository.edit(enrollment);
        }
    }

    @Override
    public void delete(Integer enrollmentCode) throws Exception {
        try (EnrollmentRepository enrollmentRepository = new EnrollmentRepository()){
            enrollmentRepository.delete(enrollmentCode);
        }
    }

    @Override
    public List<Enrollment> findAll() throws Exception {
        try  (EnrollmentRepository enrollmentRepository = new EnrollmentRepository()) {
            return enrollmentRepository.findAll();
        }
    }

    @Override
    public Enrollment findById(Integer enrollmentCode) throws Exception {
        try (EnrollmentRepository enrollmentRepository = new EnrollmentRepository()) {
            return enrollmentRepository.findById(enrollmentCode);
        }
    }
}
