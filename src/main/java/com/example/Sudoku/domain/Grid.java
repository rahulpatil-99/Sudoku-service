package com.example.Sudoku.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(creatorVisibility = ANY, fieldVisibility = ANY)
public class Grid {

    private final ArrayList<ArrayList<String>> rows;

    @JsonCreator
    public Grid(@JsonProperty("rows") ArrayList<ArrayList<String>> rows) {
        this.rows = rows;
    }

    public ArrayList<ArrayList<String>> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "rows=" + rows +
                '}';
    }
}
