package be.vdab.wijnhuis.valueobjects;

//Imports
import be.vdab.wijnhuis.entities.BestelBonLijnPK;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="bestelbonlijnen")
@IdClass(BestelBonLijnPK.class)
public class BestelBonLijn implements Serializable {

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
