package educationalSystem.model.service;

import educationalSystem.model.entity.Celass;
import educationalSystem.model.repository.CelassRepository;
import lombok.Getter;

import java.util.List;

public class CelassService  implements Service<Celass, Integer> {
    @Getter
    private static CelassService service = new CelassService();

    private CelassService(){}

    @Override
    public void save(Celass celass) throws Exception {
        try (CelassRepository celassRepository = new CelassRepository()) {
            celassRepository.save(celass);
        }
    }

    @Override
    public void edit(Celass celass) throws Exception {
        try (CelassRepository celassRepository = new CelassRepository()) {
            celassRepository.edit(celass);
        }
    }

    @Override
    public void delete(Integer celasscode) throws Exception {
        try (CelassRepository celassRepository = new CelassRepository()) {
            celassRepository.delete(celasscode);
        }
    }

    public  List<Celass> findAll() throws Exception {
        try (CelassRepository celassRepository = new CelassRepository()) {
            return celassRepository.findAll();
        }
    }

    @Override
    public Celass findById(Integer celasscode) throws Exception {
        try (CelassRepository celassRepository = new CelassRepository()) {
            return celassRepository.findById(celasscode);
        }
    }
}
