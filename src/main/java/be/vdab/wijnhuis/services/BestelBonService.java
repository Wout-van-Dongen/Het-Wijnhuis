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

    //Entities
    private BestelBonLijn bonlijn;

    public void create(BestelBon bon, Map<Long, Integer> mandje) {
        BONDOA.beginTransaction();
        BONDOA.create(bon);
        
            for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
                Wijn wijn = WIJNDAO.read(entry.getKey());
                bonlijn = new BestelBonLijn(wijn, bon, entry.getValue());
                bon.addBonLijn(bonlijn);
                wijn.addBonLijn(bonlijn);
                }
                
        BONDOA.commit();
    }

}
