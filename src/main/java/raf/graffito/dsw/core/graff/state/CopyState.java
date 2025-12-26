package raf.graffito.dsw.core.graff.state;

import raf.graffito.dsw.core.graff.model.SlideElement;

import java.util.List;

public class CopyState implements State {

    private StateManager stateManager;

    public CopyState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void copy(){

        List<SlideElement> selected = stateManager.getSelectState().getSelected();

        if(selected.isEmpty()){
            System.out.println("CopyState: Nista nije selektovano");
            return;
        }

        stateManager.getClipBoard().clear();

        for(SlideElement el : selected){
            stateManager.getClipBoard().add(el.clone());
        }

        System.out.println("CopyState: Kopirano: " + stateManager.getClipBoard().size() + "elemenata.");

    }
}
