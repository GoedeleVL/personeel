package be.vdab.personeel.services;

import be.vdab.personeel.WerknemerNietGevondenException;
import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultWerknemerService implements WerknemerService {

    private final WerknemerRepository repository;

    public DefaultWerknemerService(WerknemerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Werknemer> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Werknemer> findByChefIsNull() {
        return repository.findByChefIsNull();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Werknemer> findByJobtitel(Jobtitel jobtitel) {
        return repository.findByJobtitel(jobtitel);
    }

    @Override
    public List<Werknemer> findByChef(Werknemer chef) {
        return repository.findByChef(chef);
    }

    @Override
    public void opslag(long id, BigDecimal bedrag) {
        repository.findById(id)
                .orElseThrow(WerknemerNietGevondenException::new)
                .opslag(bedrag);
    }
}
