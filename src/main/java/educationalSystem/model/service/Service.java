package educationalSystem.model.service;

import java.util.List;


    // Generic
    public interface Service<T, I> {
        void save(T t) throws  Exception;
        void edit(T t) throws Exception;
        void delete(I id) throws Exception;
        List<T> findAll() throws Exception;
        T findById(I id) throws Exception;
    }

