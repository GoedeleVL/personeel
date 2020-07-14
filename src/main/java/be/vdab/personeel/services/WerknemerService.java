package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WerknemerService {
    Optional<Werknemer> findById(long id);
    Optional<Werknemer> findByChefIsNull();
    List<Werknemer> findByJobtitel(Jobtitel jobtitel);
    List<Werknemer> findByChef(Werknemer chef);
    void opslag(long id, BigDecimal bedrag);
}
