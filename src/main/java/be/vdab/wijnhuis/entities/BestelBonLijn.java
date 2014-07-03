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


    @SuppressWarnings({"OverridableMethodCallInConstructor", "javadoc"})
    public BestelBonLijn(Wijn wijn, BestelBon bon, int aantal) {
        this.setBestelBon(bon);
        this.setWijn(wijn);
        this.setAantal(aantal);
    }

    //Getters
    public BestelBon getBestelBon() {
        return bestelbon;
    }

    public long getBonNr() {
        return bestelbon.getBonNr();
    }

    public Wijn getWijn() {
        return wijn;
    }

    public long getWijnNr() {
        return wijn.getWijnNr();
    }

    public long getAantal() {
        return aantal;
    }

    //Setters
    public void setBestelBon(BestelBon bon) {
        this.bestelbon = bon;
        setBonNr(bestelbon.getBonNr());
    }

    public void setBonNr(long bonNr) {
        this.bonNr = bonNr;
    }

    public void setWijn(Wijn wijn) {
        this.wijn = wijn;
        setWijnNr(wijn.getWijnNr());
    }

    public void setWijnNr(long wijnNr) {
        this.wijnNr = wijnNr;
    }

    public void setAantal(long aantal) {
        this.aantal = aantal;
    }

}
