package educationalSystem.model.service;

import educationalSystem.model.entity.Report;
import educationalSystem.model.repository.ReportRepository;
import lombok.Getter;

import java.util.List;

public class ReportService implements Service<Report, Integer> {

    @Getter
    private static ReportService service =  new ReportService();

    private ReportService() {
    }

    @Override
    public void save(Report report) throws Exception {
        try (ReportRepository reportRepository = new ReportRepository()) {
            reportRepository.save(report);
        }
    }

    @Override
    public void edit(Report report) throws Exception {
        try (ReportRepository reportRepository = new ReportRepository()) {
            reportRepository.edit(report);
        }
    }

    @Override
    public void delete(Integer reportCode) throws Exception {
        try (ReportRepository reportRepository = new ReportRepository()) {
            reportRepository.delete(reportCode);
        }
    }

    @Override
    public List<Report> findAll() throws Exception {
        try (ReportRepository reportRepository = new ReportRepository()) {
            return reportRepository.findAll();
        }
    }

    @Override
    public Report findById(Integer reportCode) throws Exception {
        try (ReportRepository reportRepository = new ReportRepository()) {
            return reportRepository.findById(reportCode);
        }
    }
}
