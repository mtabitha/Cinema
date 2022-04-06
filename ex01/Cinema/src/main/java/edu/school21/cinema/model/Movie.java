package edu.school21.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String title;
    @JsonIgnore
    private String year;

    @JsonIgnore
    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @JsonIgnore
    private String description;

    private String posterLink;

    public Movie(String title, String year, String description, AgeRestriction ageRestriction) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.ageRestriction = ageRestriction;
    }

    @JsonIgnore
    public boolean isValid() {
        return !title.isEmpty() && !year.isEmpty() && !description.isEmpty();
    }
}
