package org.example.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "poems")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Poem extends AbstractEntity {
    @Column
    @JsonProperty("text")
    private String text;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("publishDate")
    private Date publishDate;

    @OneToOne
    @JoinColumn(name = "author_id")
    @JsonProperty("author_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Author author;
}
