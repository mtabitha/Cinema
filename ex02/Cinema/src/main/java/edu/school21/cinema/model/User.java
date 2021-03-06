package edu.school21.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private String          ip;

    @OneToOne
    private Image          avatar;

    public String getDate() {
        return dateTime.getMonth() + " " + dateTime.format(DateTimeFormatter.ofPattern("dd, yyyy"));
    }

    public String getTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public User(LocalDateTime dateTime, String ip/*, List<Message> messages*/) {
        this.dateTime = dateTime;
        this.ip = ip;
//        this.messages = messages;
    }

    public String getDateTime() {
        return getTime();
    }
}
