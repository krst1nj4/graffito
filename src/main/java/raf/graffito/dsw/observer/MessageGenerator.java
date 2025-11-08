package raf.graffito.dsw.observer;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher{

    private String content;
    private String type;
    private String timestamp;
    private List<Subscriber> subs;



    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber == null) return;

        if(subs == null) this.subs = new ArrayList<>();

        if(this.subs.contains(subscriber)) return;

        this.subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber == null || this.subs == null || !this.subs.contains(subscriber)) return;

        this.subs.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Poruka notify) {
        if(notify == null || this.subs == null || this.subs.isEmpty()) return;

        for(Subscriber subscriber : this.subs){
            subscriber.update(notify);
        }
    }


}
