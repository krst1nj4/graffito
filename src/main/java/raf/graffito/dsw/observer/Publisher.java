package raf.graffito.dsw.observer;

import raf.graffito.dsw.repozitorijum.composite.GraffNodeComposite;

public interface Publisher {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers(Object notify);
}
