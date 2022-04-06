package edu.school21.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Hall hall;

    @ManyToOne
    @JoinColumn
    private Movie movie;

    private Date time;

    @JsonIgnore
    private Integer price;

    public Session(Date time, Integer price) {
        this.time = time;
        this.price = price;
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm").format(time);
    }
}
