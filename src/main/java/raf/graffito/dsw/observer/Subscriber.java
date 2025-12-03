package raf.graffito.dsw.observer;

import raf.graffito.dsw.core.messages.Poruka;

public interface Subscriber {
    void update(Poruka notif);
}
