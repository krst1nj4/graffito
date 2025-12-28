package raf.graffito.dsw.core.graff.strategy;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class KomandaManager {

    private List<RadnjaStrategy> komande = new ArrayList<>();
    private int trenutnaKomanda = 0;

    public void dodajKomandu(RadnjaStrategy komanda) {
        while (trenutnaKomanda < komande.size()) {
            komande.remove(trenutnaKomanda);
        }
        komande.add(komanda);
        komanda.izvrsi(); // Odmah izvrsava radnju
        trenutnaKomanda++;
        osveziPrikaz();
    }

    public void undo() {
        if (trenutnaKomanda > 0) {
            trenutnaKomanda--;
            komande.get(trenutnaKomanda).ponisti();
            osveziPrikaz();
        }
    }

    public void redo() {
        if (trenutnaKomanda < komande.size()) {
            komande.get(trenutnaKomanda).izvrsi();
            trenutnaKomanda++;
            osveziPrikaz();
        }
    }

    private void osveziPrikaz() {
        if(MainFrame.getInstance().getProjectView() != null) {
            MainFrame.getInstance().getProjectView().repaint();
        }
    }
}
