package be.vdab.personeel.repositories;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    Optional<Werknemer> findByChefIsNull();
    List<Werknemer> findByJobtitel(Jobtitel jobtitel);
    List<Werknemer> findByChef(Werknemer chef);
}
