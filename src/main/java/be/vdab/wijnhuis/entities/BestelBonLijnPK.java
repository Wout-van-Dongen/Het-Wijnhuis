package be.vdab.wijnhuis.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class BestelBonLijnPK implements Serializable {

    private final static long serialVersionUID = 1L;

    private long bonNr, wijnNr;

    protected BestelBonLijnPK() {
    }

    public BestelBonLijnPK(long bonNr, long wijnNr) {
        this.bonNr = bonNr;
        this.wijnNr = wijnNr;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            BestelBonLijnPK bblPK = (BestelBonLijnPK) obj;
            return (bblPK.bonNr == bonNr && bblPK.wijnNr == wijnNr);
        } catch (NullPointerException nullExc) {
            return false;
        }
    }

    //Getters
    public long getBonNr() {
        return bonNr;
    }

    public long getWijnNr() {
        return wijnNr;
    }
    
    //Setters
    
    public void setWijnNr(long wijnNr){
        this.wijnNr = wijnNr;
    }
    
        public void setBonNr(long bonNr){
        this.bonNr = bonNr;
    }

}
