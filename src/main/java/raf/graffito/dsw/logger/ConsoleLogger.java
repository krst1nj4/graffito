package raf.graffito.dsw.logger;

import raf.graffito.dsw.observer.Poruka;

public class ConsoleLogger implements Logger {

    @Override
    public void log(Poruka poruka) {
        System.out.println(poruka.toString());
    }

    @Override
    public void update(Poruka notif) {
        log(notif);
    }
}
