package edu.school21.cinema.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_SEQ")
    private Long id;

    private Long channelId;

    private String text;

    private LocalDateTime dateTime;

    public Message(Long channelId, String text, LocalDateTime dateTime) {
        this.channelId = channelId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    @ManyToOne
    private User user;
}
