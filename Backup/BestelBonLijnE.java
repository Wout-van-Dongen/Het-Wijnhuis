package be.vdab.wijnhuis.entities;

//Imports
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="bestelbonlijnen")
@IdClass(BestelBonLijnPK.class)
public class BestelBonLijnE implements Serializable {

    private static final long serialVersionUID = 1L;
    
@Id
@ManyToOne
@JoinColumn(name="BonNr")
private long bonNr;
@Id
@ManyToOne
@JoinColumn(name="WijnNr")
private long wijnNr;

@Column(name="Aantal")
private long  aantal;



}
