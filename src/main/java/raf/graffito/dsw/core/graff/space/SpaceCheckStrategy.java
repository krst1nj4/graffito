package raf.graffito.dsw.core.graff.space;

import raf.graffito.dsw.core.graff.model.Slide;

public interface SpaceCheckStrategy {
    boolean hasEnoughSpace(Slide slide, int newX, int newY,  int newWidth, int newHeight);
}
