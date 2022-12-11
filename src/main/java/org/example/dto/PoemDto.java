package org.example.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class PoemDto {
    @JsonProperty("text")
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("publishDate")
    private Date publishDate;
    @JsonProperty("authorId")
    private Long authorId;

    @JsonCreator
    public PoemDto(
            @JsonProperty("text") String text,
            @JsonProperty("publishDate") Date publishDate,
            @JsonProperty("author_id") Long authorId) {
        this.text = text;
        this.publishDate = publishDate;
        this.authorId = authorId;
    }
}
