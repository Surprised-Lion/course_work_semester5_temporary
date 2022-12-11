package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.example.common.GenderType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authors")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Author extends AbstractEntity {
    @Column
    @JsonProperty("firstName")
    private String firstName;

    @Column
    @JsonProperty("lastName")
    private String lastName;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("dateOfDeath")
    private Date dateOfDeath;

    @Column
    @Enumerated(EnumType.STRING)
    @JsonProperty("genderType")
    private GenderType genderType;

    @Column
    @JsonProperty("info")
    private String info;
}
