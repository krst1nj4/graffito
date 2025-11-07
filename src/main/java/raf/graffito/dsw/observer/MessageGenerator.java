package raf.graffito.dsw.observer;

import raf.graffito.dsw.model.MessageType;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher{

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
    public void notifySubscribers(Object notify) {
        for(Subscriber subscriber : subscribers){
            subscriber.update(notify);
        }
    }
}
