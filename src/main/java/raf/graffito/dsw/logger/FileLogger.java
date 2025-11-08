package raf.graffito.dsw.logger;

import raf.graffito.dsw.observer.Poruka;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger {

    private static final String LOG_FILE = "log.txt";


    @Override
    public void log(Poruka poruka) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.println(poruka.toString());
        } catch (IOException e) {
            System.err.println("Greska pri upisu u fajl: " + e.getMessage());
        }
    }

    @Override
    public void update(Poruka notif) {
        log(notif);
    }
}
