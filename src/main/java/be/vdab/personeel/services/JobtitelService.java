package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;

import java.util.List;
import java.util.Optional;

public interface JobtitelService {
    Optional<Jobtitel> findById(long id);
    List<Jobtitel> findAll();

}
