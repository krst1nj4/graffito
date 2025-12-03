package raf.graffito.dsw.core.messages;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter

public class Poruka {
    private String content;
    private MessageType type;
    private String timestamp;

    public Poruka(String content, MessageType type) {
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));

    }

    @Override
    public String toString() {
        return "[" + type + "] " + "[" + timestamp + "] " + content;
    }
}
