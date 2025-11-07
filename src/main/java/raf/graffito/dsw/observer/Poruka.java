package raf.graffito.dsw.observer;

import raf.graffito.dsw.model.MessageType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Poruka {
    private String content;
    private String type;
    private String timestamp;

    public Poruka(String content, MessageType type) {
        this.content = content;
        this.type = type.name();
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));
    }

    @Override
    public String toString() {
        return "[" + type + "] " + "[" + timestamp + "] " + content;
    }
}
