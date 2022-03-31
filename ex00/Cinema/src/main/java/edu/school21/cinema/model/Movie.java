package edu.school21.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILM_SEQ")
    private Long id;

    private String title;
    private String year;

    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    private String description;

    private String posterLink;

    public Movie(String title, String year, String description, AgeRestriction ageRestriction) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.ageRestriction = ageRestriction;
    }

    public boolean isValid() {
        return !title.isEmpty() && !year.isEmpty() && !description.isEmpty();
    }
}
