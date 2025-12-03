package raf.graffito.dsw.core.logger;

import raf.graffito.dsw.core.messages.Poruka;
import raf.graffito.dsw.observer.Subscriber;

public interface Logger extends Subscriber {
    void log(Poruka poruka);
}
