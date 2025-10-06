package educationalSystem.model.service;

import educationalSystem.model.entity.Exersise;
import educationalSystem.model.repository.ExersiseRepository;
import lombok.Getter;

import java.util.List;

public class ExersiseService implements Service<Exersise, Integer> {

    @Getter
    private static ExersiseService service = new ExersiseService();

    private ExersiseService() {
    }


    @Override
    public void save(Exersise exersise) throws Exception {
        try (ExersiseRepository exersiseRepository = new ExersiseRepository()) {
            exersiseRepository.save(exersise);
        }
    }

    @Override
    public void edit(Exersise exersise) throws Exception {
        try (ExersiseRepository exersiseRepository = new ExersiseRepository()) {
            exersiseRepository.edit(exersise);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ExersiseRepository exersiseRepository = new ExersiseRepository()) {
            exersiseRepository.delete(id);
        }
    }

    @Override
    public List<Exersise> findAll() throws Exception {
        try (ExersiseRepository exersiseRepository = new ExersiseRepository()) {
            return exersiseRepository.findAll();
        }
    }

    @Override
    public Exersise findById(Integer id) throws Exception {
        try (ExersiseRepository exersiseRepository = new ExersiseRepository()) {
            return exersiseRepository.findById(id);
        }
    }

}

