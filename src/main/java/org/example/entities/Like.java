package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "likes")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @JsonProperty("sendingTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime sendingTime;

    @OneToOne
    @JoinColumn(name = "author_id")
    @JsonProperty("author_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Author author;

    @OneToOne
    @JoinColumn(name = "poem_id")
    @JsonProperty("poem_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Poem poem;

    public Like(User user, Author author, Poem poem) {
        this.user = user;
        this.author = author;
        this.sendingTime = LocalDateTime.now();
        this.poem = poem;
    }
}
