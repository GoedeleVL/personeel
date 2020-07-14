package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultJobtitelService implements JobtitelService {

    private final JobtitelRepository repository;

    public DefaultJobtitelService(JobtitelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Jobtitel> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jobtitel> findAll() {
        return repository.findAll();
    }
}
