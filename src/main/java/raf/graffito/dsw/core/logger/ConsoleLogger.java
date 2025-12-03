package raf.graffito.dsw.core.logger;

import raf.graffito.dsw.core.messages.Poruka;

public class ConsoleLogger implements Logger {

    @Override
    public void update(Object notif) {
        System.out.println(notif.toString());
    }
}
