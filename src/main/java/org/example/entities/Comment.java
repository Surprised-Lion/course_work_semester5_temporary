package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractEntity {
    @Column
    @JsonProperty("text")
    private String text;

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

    @ManyToOne
    @JoinColumn(name = "poem_id")
    @JsonProperty("poem_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Poem poem;

    public Comment(String text, User user, Poem poem) {
        this.text = text;
        this.user = user;
        this.sendingTime = LocalDateTime.now();
        this.poem = poem;
    }
}
