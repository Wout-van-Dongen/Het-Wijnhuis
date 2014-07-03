package be.vdab.wijnhuis.entities;

//Imports
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "bestelbonnen")
//Entity from the table 'bestelbonnen''
public class BestelBon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "BonNr")
    // Auto-generated primary key from the column 'BonNr'
    private long bonNr;
    @Temporal(TemporalType.DATE)
    @Column(name = "BestelDatum")
    //Variable van het type DATE
    private Date bestelDatum;
    @Column(name = "Naam")
    private String naam;
    @Column(name = "Straat")
    private String straat;
    @Column(name = "HuisNr")
    private String huisNr;
    @Column(name = "PostCode")
    private String postcode;
    @Column(name = "Gemeente")
    private String gemeente;
    @Column(name = "BestelWijze")
    private int bestelWijze;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bestelbon")
    private Set<BestelBonLijn> bestelBonLijnen;

    //Constructors
    protected BestelBon() {
        /**
         * Default constructor Needs to be present in JPA But is inaccessible
         * and does nothing here
         */
    }

    public BestelBon(Date bestelDatum, String naam, String straat, String huisNr, String postcode, String gemeente, int bestelWijze) {
        this.bestelDatum = bestelDatum;
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.bestelWijze = bestelWijze;
        bestelBonLijnen = new LinkedHashSet<>();
    }

    //Adders
    public void addBonLijn(BestelBonLijn bonLijn) {
        bestelBonLijnen.add(bonLijn);
    }

    //Getters
    public long getBonNr() {
        return bonNr;
    }

    public Date getBestelDatum() {
        return bestelDatum;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getBestelWijze() {
        return bestelWijze;
    }

    public Set<BestelBonLijn> getBonLijnen() {
        return Collections.unmodifiableSet(bestelBonLijnen);
    }

    //Setters
    public void setBonNr(long bonNr) {
        this.bonNr = bonNr;
    }

    public void setBestelDatum(Date bestelDatum) {
        this.bestelDatum = bestelDatum;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisNr(String huisNr) {
        this.huisNr = huisNr;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public void setBestelWijze(int bestelWijze) {
        this.bestelWijze = bestelWijze;
    }

}
