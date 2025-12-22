package raf.graffito.dsw.core.graff.model;

import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;
import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Slide extends GraffNodeComposite implements Publisher {
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();

    public Slide(String Name, GraffNode parentNode) {
        super(parentNode, Name);

        setName(Name);
        setParent(parentNode);
    }

    @Override
    public void addChild(GraffNode cvor) {
        getChildren().add(cvor);
        notifySubscribers(cvor);
    }

    @Override
    public void removeChild(GraffNode cvor) {
        getChildren().remove(cvor);
        notifySubscribers(cvor);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
