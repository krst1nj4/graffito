package raf.graffito.dsw.core.messages;

import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {

    private List<Subscriber> subscribers;

    public void generateMessage(MessageType messageType, String poruka){
        Poruka message = new Poruka(poruka, messageType);
        notifySubscribers(message);
    }

    public MessageGenerator() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
            subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Poruka notify) {
        if(notify == null || this.subscribers == null || this.subscribers.isEmpty()) return;

        for(Subscriber subscriber : this.subscribers){
            subscriber.update(notify);
        }
    }
}
