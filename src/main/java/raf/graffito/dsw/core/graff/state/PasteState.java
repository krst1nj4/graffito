package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.SlideElement;
import raf.graffito.dsw.gui.swing.views.SlideView;

public class PasteState implements State {

    private StateManager stateManager;

    public PasteState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void paste(SlideView view){

        if(stateManager.getClipBoard().isEmpty()){
            System.out.println("PasteState: Clipboard je prazan");
            return;
        }

        if(view == null){
            System.out.println("SlideView je null");
            return;
        }

        stateManager.getSelectState().getSelected().clear();

        for(SlideElement el : stateManager.getClipBoard()){

            SlideElement novi = el.clone();

            novi.setX(novi.getX() + 20);
            novi.setY(novi.getY() + 20);

            view.getSlide().addChild(novi);

            stateManager.getSelectState().getSelected().add(novi);
        }

        view.repaint();
        System.out.println("PasteState: Nalepljeno" + stateManager.getClipBoard().size() + "elemenata.");
    }
}
