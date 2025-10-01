package educationalSystem.model.service;

import educationalSystem.model.entity.Attendance;

import educationalSystem.model.repository.AttendanceRepository;
import lombok.Getter;

import java.util.List;

public class AttendanceService {
    @Getter
    private static AttendanceService service = new AttendanceService();

    private AttendanceService() {
    }


    @Override
    public void save(Attendance attendance) throws Exception {
        try (AttendanceRepository attendanceRepository = new AttendanceRepository()) {
            attendanceRepository.save(attendance);
        }
    }

    @Override
    public void edit(Attendance attendance) throws Exception {
        try (AttendanceRepository attendanceRepository = new AttendanceRepository()) {
            attendanceRepository.edit(attendance);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (AttendanceRepository attendanceRepository = new AttendanceRepository()) {
            attendanceRepository.delete(id);
        }
    }

    @Override
    public List<Attendance> findAll() throws Exception {
        try (AttendanceRepository attendanceRepository = new AttendanceRepository()) {
            return attendanceRepository.findAll();
        }
    }

    @Override
    public Attendance findById(Integer attendance_id) throws Exception {
        try (AttendanceRepository attendanceRepository = new AttendanceRepository()) {
            return attendanceRepository.findById(attendance_id);
        }
    }
}
