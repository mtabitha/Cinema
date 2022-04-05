package edu.school21.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HALL_SEQ")
    private Long id;

    private Integer size;

    public Hall(Integer size) {
        this.size = size;
    }
}
