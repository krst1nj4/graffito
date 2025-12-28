package raf.graffito.dsw.core.graff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.core.graff.composite.GraffNodeLeaf;
import raf.graffito.dsw.core.graff.strategy.KomandaManager;
import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Slide extends GraffNodeComposite implements Publisher {
    @JsonIgnore
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private KomandaManager  komandaManager;
    public Slide(String Name, GraffNode parentNode) {
        super(parentNode, Name);
        this.komandaManager = new KomandaManager();

        setName(Name);
        setParent(parentNode);
    }

    public Slide() {
        super();
        this.komandaManager = new KomandaManager();
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
