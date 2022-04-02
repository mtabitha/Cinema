package edu.school21.cinema.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long channelId;

    private String text;

    public Message(Long channelId, String text) {
        this.channelId = channelId;
        this.text = text;
    }

    @ManyToOne
    private User user;
}
