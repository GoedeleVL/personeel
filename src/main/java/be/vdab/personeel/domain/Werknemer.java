package be.vdab.personeel.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "werknemers")
public class Werknemer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String familienaam;
    private String voornaam;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefid")
    private Werknemer chef;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal salaris;
    private String paswoord;
    @DateTimeFormat(pattern = "d-M-yy")
    private LocalDate geboorte;
    @Version
    private int versie;

    protected Werknemer() {
    }

    public Werknemer(String familienaam, String voornaam, String email,
                     Werknemer chef, Jobtitel jobtitel, BigDecimal salaris,
                     String paswoord, LocalDate geboorte) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        setChef(chef);
        setJobtitel(jobtitel);
        this.salaris = salaris;
        this.paswoord = new BCryptPasswordEncoder().encode(paswoord);
        this.geboorte = geboorte;
    }

    public void opslag(BigDecimal bedrag) {
        if (bedrag.compareTo(BigDecimal.ONE) <= 0) {
            throw new IllegalArgumentException();
        }
        salaris = salaris.add(bedrag);
    }

    public void setJobtitel(Jobtitel jobtitel) {
        this.jobtitel = jobtitel;
    }

    public void setChef(Werknemer chef) {
        if (chef == this) {
            throw new IllegalArgumentException();
        }
        this.chef = chef;
    }

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public int getVersie() {
        return versie;
    }
}
