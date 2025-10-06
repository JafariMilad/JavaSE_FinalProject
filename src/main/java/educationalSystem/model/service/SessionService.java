package educationalSystem.model.service;

import educationalSystem.model.entity.Session;
import educationalSystem.model.repository.SessionRepository;
import lombok.Getter;

import java.util.List;

public class SessionService implements Service<Session, Integer> {
    @Getter
    private static SessionService service = new SessionService();

    private SessionService(){}

    @Override
    public void save(Session session) throws Exception {
        try(SessionRepository sessionRepository = new SessionRepository()) {
            sessionRepository.save(session);
        }
    }

    @Override
    public void edit(Session session) throws Exception {
        try(SessionRepository sessionRepository = new SessionRepository()) {
            sessionRepository.edit(session);
        }
    }

    @Override
    public void delete(Integer sessionCode) throws Exception {
        try(SessionRepository sessionRepository = new SessionRepository()) {
            sessionRepository.delete(sessionCode);
        }
    }

    @Override
    public List<Session> findAll() throws Exception {
        try(SessionRepository sessionRepository = new SessionRepository()) {
            return sessionRepository.findAll();
        }
    }

    @Override
    public Session findById(Integer sessionCode) throws Exception {
        try(SessionRepository sessionRepository = new SessionRepository()) {
            return sessionRepository.findById(sessionCode);
        }
    }
}
