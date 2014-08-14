package be.vdab.wijnhuis.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "bestelbonlijnen")
public class BestelBonLijn implements Serializable {

    private static final long serialVersionUID = 1L;

    //Sets the Combined ID
    @Id
    @Column(name = "WijnNr")
    private long wijnNr;

    @Id
    @Column(name = "BonNr")
    private long bonNr;

//The extra variable
    @Column(name = "Aantal")
    private int aantal;

//The objects holding the pk
    @ManyToOne @Transient
    @JoinColumn(name = "wijnNr")
    private Wijn wijn;

    @ManyToOne @Transient
    @JoinColumn(name = "bonNr")
    private BestelBon bon;

    //Default Constructor
    protected BestelBonLijn() {

    }

    //Getters & Setters
    public long getWijnNr() {
        return wijnNr;
    }

    public void setWijnNr(long wijnNr) {
        this.wijnNr = wijnNr;
    }

    public long getBonNr() {
        return bonNr;
    }

    public void setBonNr(long bonNr) {
        this.bonNr = bonNr;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Wijn getWijn() {
        return wijn;
    }

    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
    }

    public BestelBon getBon() {
        return bon;
    }

    public void setBon(BestelBon bon) {
        this.bon = bon;
    }

}
