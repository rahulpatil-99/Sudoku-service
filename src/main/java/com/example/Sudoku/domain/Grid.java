package com.example.Sudoku.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(creatorVisibility = ANY, fieldVisibility = ANY)
public class Grid {

    private final List<String> details;

    @JsonCreator
    public Grid(@JsonProperty("details") List<String> details) {
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "details=" + details +
                '}';
    }
}
