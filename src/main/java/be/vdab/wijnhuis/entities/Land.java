package be.vdab.wijnhuis.entities;

//Imports
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "landen")
// Entity from the table 'Landen'
public class Land {

    @Id
    @GeneratedValue
    @Column(name = "LandNr")
    // Auto-generated primary key from the column 'LandNr'
    private long landNr;
    @Column(name = "Naam")
    private String naam;

    //Soorten
    @OneToMany(mappedBy = "land") @OrderBy("naam")
    //List of all 'soorten' having the variable 'land' set to this 'land', ordered by the variable 'name'
    private Set<Soort> soorten;

    protected Land() {
        /**
         * Default constructor Needs to be present in JPA But is inaccessible
         * and does nothing here
		 *
         */
    }

    // Actual constructor
    public Land(String naam) {
        setNaam(naam);
    }

	// Setters
    public void setNaam(String naam) {
        this.naam = naam;
    }

	// Getters
    public String getNaam() {
        return naam;
    }

    public long getLandNr() {
        return landNr;
    }

    public Set<Soort> getSoorten() {
        return soorten;
    }
}
