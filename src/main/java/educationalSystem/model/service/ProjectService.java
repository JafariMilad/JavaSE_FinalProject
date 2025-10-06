package educationalSystem.model.service;

import educationalSystem.model.entity.Project;
import educationalSystem.model.repository.ProjectRepository;
import lombok.Getter;

import java.util.List;

public class ProjectService  implements Service<Project, Integer> {

    @Getter
    private static ProjectService service = new ProjectService();

    private ProjectService() {
    }


    @Override
    public void save(Project project) throws Exception {
        try (ProjectRepository projectRepository = new ProjectRepository()) {
            projectRepository.save(project);
        }
    }

    @Override
    public void edit(Project project) throws Exception {
        try (ProjectRepository projectRepository = new ProjectRepository()) {
            projectRepository.edit(project);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ProjectRepository projectRepository = new ProjectRepository()) {
            projectRepository.delete(id);
        }
    }

    @Override
    public List<Project> findAll() throws Exception {
        try (ProjectRepository projectRepository = new ProjectRepository()) {
            return projectRepository.findAll();
        }
    }

    @Override
    public Project findById(Integer id) throws Exception {
        try (ProjectRepository projectRepository = new ProjectRepository()) {
            return projectRepository.findById(id);
        }
    }

}