package be.vdab.personeel.services;

import be.vdab.personeel.WerknemerNietGevondenException;
import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultWerknemerServiceTest {

    private DefaultWerknemerService service;
    private Werknemer werknemer;
    private static final BigDecimal SALARIS = BigDecimal.valueOf(200);
    @Mock
    private WerknemerRepository repository;

   @BeforeEach
    void beforeEach() {
       werknemer = new Werknemer("test", "test", "test",
               null, new Jobtitel("test"), SALARIS, "test", LocalDate.now());
       service = new DefaultWerknemerService(repository);
   }

   @Test
    void opslag() {
       when(repository.findById(1L)).thenReturn(Optional.of(werknemer));
       service.opslag(1, BigDecimal.TEN);
       assertThat(werknemer.getSalaris()).isEqualByComparingTo("210");
       verify(repository).findById(1L);
   }

   @Test
    void opslagOnbestaandeWerknemer() {
       assertThatExceptionOfType(WerknemerNietGevondenException.class)
               .isThrownBy(() -> service.opslag(-1, BigDecimal.TEN));
       verify(repository).findById(-1L);
   }


}