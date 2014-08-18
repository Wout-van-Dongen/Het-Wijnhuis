package be.vdab.wijnhuis.entities;

//Imports
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "wijnen")
// Entity from the table 'Soorten''
public class Wijn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "wijnNr")
    // Auto-generated primary key from the column 'WijnNr''
    private long wijnNr;
    @Column(name = "Jaar")
    private int jaar;
    @Column(name = "Beoordeling")
    private int beoordeling;
    @Column(name = "InBestelling")
    private int besteld;
    @Column(name = "Prijs")
    private BigDecimal prijs;

    //Soorten
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SoortNr")
    private Soort soort;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wijn")
    private Set<BestelBonLijn> bonlijnen;
    

    protected Wijn() {
        /**
         * Default constructor Needs to be present in JPA But is inaccessible
         * and does nothing here
         */
    }

    // Actual constructor
    @SuppressWarnings({"OverridableMethodCallInConstructor", "javadoc"})
    public Wijn(int jaar, Soort soort, int beoordeling, int besteld, BigDecimal prijs) {
        setJaar(jaar);
        setSoort(soort);
        setBeoordeling(beoordeling);
        setBesteld(besteld);
        setPrijs(prijs);
        bonlijnen = new HashSet<>();
    }

// Setters
    public void setBeoordeling(int beoordeling) {
        this.beoordeling = beoordeling;
    }

    public void setBesteld(int besteld) {
        this.besteld = besteld;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public void setSoort(Soort soort) {
        this.soort = soort;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

// Getters
    public Soort getSoort() {
        return soort;
    }

    public long getWijnNr() {
        return wijnNr;
    }

    public int getBesteld() {
        return besteld;
    }

    public int getJaar() {
        return jaar;
    }

    public int getBeoordeling() {
        return beoordeling;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Set<BestelBonLijn> getBonlijnen() {
        return bonlijnen;
    }

    public void setBonlijnen(Set<BestelBonLijn> bonlijnen) {
        this.bonlijnen = bonlijnen;
    }

   
}
