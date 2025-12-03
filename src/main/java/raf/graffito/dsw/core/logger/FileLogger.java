package raf.graffito.dsw.core.logger;

import raf.graffito.dsw.core.messages.Poruka;

import java.io.*;

public class FileLogger implements Logger {

    @Override
    public void update(Object notif) {
       try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/logs.txt", true))) {
           bw.write(notif.toString());
           bw.newLine();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
