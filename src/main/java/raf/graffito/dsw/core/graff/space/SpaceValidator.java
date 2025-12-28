package raf.graffito.dsw.core.graff.space;

import raf.graffito.dsw.core.graff.model.Slide;

public class SpaceValidator {

    private static SpaceValidator instance;
    private SpaceCheckStrategy strategy;

    private SpaceValidator() {
        this.strategy = new BinaryMatrixStrategy();
    }

    public static SpaceValidator getInstance() {
        if (instance == null) {
            instance = new SpaceValidator();
        }
        return instance;
    }

    public void setStrategy(SpaceCheckStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkSpace(Slide slide, int x, int y, int w, int h) {
        return strategy.hasEnoughSpace(slide, x, y, w, h);
    }

}
