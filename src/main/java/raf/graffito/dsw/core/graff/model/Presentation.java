package raf.graffito.dsw.core.graff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends GraffNodeComposite implements Publisher {
    @JsonIgnore
    @Getter
    private List<Subscriber> subscribers =  new ArrayList<>();



    public Presentation(String name, GraffNode parent) {
        super(parent, name);
    }

    public Presentation() {
        super();
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(GraffNode child) {
        if(child instanceof Slide) {
            this.getChildren().add(child);
            this.notifySubscribers(child);
        }
    }

    @Override
    public void removeChild(GraffNode child) {
        getChildren().remove(child);
    }

    public int getNumberOfSlides() {
        return getChildren().size();
    }

    public void setName(String name) {
        super.setName(name);
        notifySubscribers("presentationNameChanged");
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (Subscriber sub : subscribers) {
            sub.update(notification);
        }
    }
}
