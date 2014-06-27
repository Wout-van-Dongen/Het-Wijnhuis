package be.vdab.wijnhuis.entities;

//Imports
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "bestelbonlijnen")
@IdClass(BestelBonLijnPK.class)
public class BestelBonLijn implements Serializable {
//SerialID

    private static final long serialVersionUID = 1L;

    // Properties    
    @Id
    private long wijnNr;
    @Id
    private long bonNr;
    @Column(name = "Aantal")
    private long aantal;

    @ManyToOne
    @JoinColumn(name = "BonNr")
    private BestelBon bestelbon;

    @ManyToOne
    @JoinColumn(name = "WijnNr")
    private Wijn wijn;

//Constructors
    protected BestelBonLijn() {
        /**
         * Default constructor Needs to be present in JPA But is inaccessible
         * and does nothing here
         */
    }

    public BestelBonLijn(Wijn wijn, BestelBon bon) {
        this.bestelbon = bon;
        this.wijn = wijn;
        wijnNr = wijn.getWijnNr();
        bonNr = bestelbon.getBonNr();
    }

    //Getters
    public long getBonNr() {
        return bestelbon.getBonNr();
    }

    public long getWijnNr() {
        return wijn.getWijnNr();
    }

    //Setters

    public void setBonNr(long bonNr) {
        this.bonNr = bonNr;
    }

    public void setWijnNr(long wijnNr) {
        this.wijnNr = wijnNr;
    }

}
