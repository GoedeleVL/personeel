package be.vdab.personeel.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

class WerknemerTest {
    private final static BigDecimal SALARIS = BigDecimal.valueOf(200);
    private Werknemer werknemer1;
    private Jobtitel jobtitel1;

    @BeforeEach
    void beforeEach() {
        jobtitel1 = new Jobtitel("test");
        werknemer1 = new Werknemer("test", "test", "test", null, jobtitel1, SALARIS,
                "test", LocalDate.now());
    }

    @Test
    void opslag() {
        werknemer1.opslag(BigDecimal.TEN);
        assertThat(werknemer1.getSalaris()).isEqualByComparingTo("210");
    }

    @Test
    void opslagMetNullMislukt() {
        assertThatNullPointerException().isThrownBy(
                () -> werknemer1.opslag(null));
    }

    @Test
    void opslagMetMinderDan1Mislukt() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> werknemer1.opslag(BigDecimal.valueOf(0.5)));
    }

    @Test
    void negatieveOpslagMislukt() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> werknemer1.opslag(BigDecimal.valueOf(-1)));
    }

    @Test
    void werknemerKanNietZijnEigenChefWorden() {
        assertThatIllegalArgumentException().isThrownBy(() -> werknemer1.setChef(werknemer1));
    }
}