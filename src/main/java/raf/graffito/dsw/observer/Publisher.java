package raf.graffito.dsw.observer;

import raf.graffito.dsw.core.messages.Poruka;

public interface Publisher {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers(Poruka notify);
}
