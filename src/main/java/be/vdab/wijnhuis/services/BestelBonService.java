package be.vdab.wijnhuis.services;

import be.vdab.wijnhuis.dao.BestelBonDAO;
import be.vdab.wijnhuis.dao.WijnenDAO;
import be.vdab.wijnhuis.entities.BestelBon;
import be.vdab.wijnhuis.entities.BestelBonLijn;
import be.vdab.wijnhuis.entities.Wijn;
import java.util.Map;

public class BestelBonService {

    //Data Acces Objects
    private final BestelBonDAO BONDOA = new BestelBonDAO();
    private final WijnenDAO WIJNDAO = new WijnenDAO();

    public void create(BestelBon bon, Map<Long, Integer> mandje) {

        BestelBon bestelbon = bon;

        //For every wijn in the basket...
        for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
            BestelBonLijn bestelbonLijn;
            Wijn wijn;

            //Read in the wijn
            wijn = WIJNDAO.read(entry.getKey());

            //Create a new  bestelbonlijn using the bon and  current wijn    
            bestelbonLijn = new BestelBonLijn(wijn, bon, entry.getValue());

            //Add the bestelbonLijn to the array in bestelbon
            bon.addBonLijn(bestelbonLijn);
            //Add the bestelbonLijn to the array in bestelbon
            wijn.addBonLijn(bestelbonLijn);
        }
        //Begins the transaction
        BONDOA.beginTransaction();
        //Tells hibernate to add the bestelbon to the database
        BONDOA.create(bon);
        //confirm the transaction
        BONDOA.commit();
    }

}
