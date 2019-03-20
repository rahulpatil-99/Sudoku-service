package com.example.Sudoku.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(creatorVisibility = ANY, fieldVisibility = ANY)
public class Grid {

    private final ArrayList<ArrayList<String>> details;

    @JsonCreator
    public Grid(@JsonProperty("details") ArrayList<ArrayList<String>> details) {
        this.details = details;
    }

    public ArrayList<ArrayList<String>> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "details=" + details +
                '}';
    }
}
