package raf.graffito.dsw.core.logger;

import raf.graffito.dsw.core.messages.Poruka;

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
