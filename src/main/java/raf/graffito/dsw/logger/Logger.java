package raf.graffito.dsw.logger;

import raf.graffito.dsw.observer.Poruka;
import raf.graffito.dsw.observer.Subscriber;

public interface Logger extends Subscriber {
    void log(Poruka poruka);
}
