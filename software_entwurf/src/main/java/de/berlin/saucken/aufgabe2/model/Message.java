package de.berlin.saucken.aufgabe2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Message {

    private String content;

    private User sender;

    private LocalDateTime sentAt;
}
