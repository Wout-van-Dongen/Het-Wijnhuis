package be.vdab.wijnhuis.entities;

//Imports
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "soorten")
// Entity from the table 'Soorten''
public class Soort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "SoortNr")
    // Auto-generated primary key from the column 'SoortNr'
    private long soortNr;
    @Column(name = "Naam")
    private String naam;
    
    //Land
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LandNr")
    private Land land;
    
    //Wijnen
    @OneToMany(mappedBy = "soort") @OrderBy("jaar") 
//list of all wines having the variable 'soort' set to this soort , ordered by variable 'jaar'
    private Set<Wijn > wijnen;
    

    protected Soort() {
        /**
         * Default constructor 
         * Needs to be present in JPA But is inaccessible
         * and does nothing here
         */
    }

    // Actual constructor
    public Soort(String naam, Land land) {
        wijnen = new LinkedHashSet<>();
        setNaam(naam);
        setLand(land);
    }

    //Setters
    public void setNaam(String naam){this.naam = naam;}
    public void setLand(Land land){this.land = land;}
    
// Getters
    public String getNaam() { return naam; }    
    public long getSoortNr() { return soortNr;}    
    public Land getLand() { return land;}
    public Set<Wijn> getWijnen(){return wijnen;}
}
