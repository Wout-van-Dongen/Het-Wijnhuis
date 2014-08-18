package be.vdab.wijnhuis.services;

import be.vdab.wijnhuis.dao.BestelBonDAO;
import be.vdab.wijnhuis.dao.BestelBonLijnDAO;
import be.vdab.wijnhuis.dao.WijnenDAO;
import be.vdab.wijnhuis.entities.BestelBon;
import be.vdab.wijnhuis.entities.BestelBonLijn;
import be.vdab.wijnhuis.entities.Wijn;
import java.util.Map;

public class BestelBonLijnService {

    private WijnenDAO wijnDAO;
    private BestelBonLijnDAO bblDAO;

    public void create(Wijn wijn, BestelBon bon, int aantal) {
        bblDAO.create(new BestelBonLijn(wijn, bon, aantal));
    }

    public void create(Map<Long, Integer> winkelmand, BestelBon bon) {
        for (Map.Entry<Long, Integer> e : winkelmand.entrySet()) {
            create(wijnDAO.read(e.getKey()), bon, e.getValue());
        }
    }

}
