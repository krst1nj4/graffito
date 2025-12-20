package raf.graffito.dsw.core.graff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.graffito.dsw.core.graff.composite.GraffNode;
import raf.graffito.dsw.core.graff.composite.GraffNodeComposite;
import raf.graffito.dsw.observer.Publisher;
import raf.graffito.dsw.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Project extends GraffNodeComposite implements Publisher {
    private String author;

    @JsonIgnore
    private List<Subscriber> subscribers = new ArrayList<>();

    @JsonIgnore
    @Getter
    private String filePath;

    @JsonIgnore
    private boolean changed = false;

    public Project(String name, GraffNode parent, String author) {
        super(parent, name);
        this.author = author;
    }

    public int getNumber(){
        int number = 0;

        for(GraffNode child : getChildren()){
            if(child instanceof Presentation){
                number += ((Presentation) child).getNumberOfSlides();
            } else {
                number++;
            }
        }

        return number;
    }

    @Override
    public void addChild(GraffNode child) {
        getChildren().add(child);
        this.changed = true;
        notifySubscribers(child);
    }

    @Override
    public void removeChild(GraffNode child) {
        getChildren().remove(child);
        this.changed = true;
        notifySubscribers(child);
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.changed = true;
        notifySubscribers("projectNameChanged");
    }

    public void setAuthor(String author) {
        this.author = author;
        this.changed = true;
        notifySubscribers("projectAuthorChanged");
    }
}
