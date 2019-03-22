package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SudokuService {

    public Boolean validate(Grid grid) {
        return DoesHaveUniqueElements(grid) && DoesHaveUniqueElements(transpose(grid));
    }

    public boolean DoesHaveUniqueElements(Grid grid) {
        return grid.getDetails()
                .stream()
                .allMatch(s-> s.size() == s.stream().distinct().toArray().length);
    }

    public Grid transpose(Grid grid) {
        ArrayList<ArrayList<String>> rows = grid.getDetails();
        ArrayList<ArrayList<String>> columns = createColumnsOfSize(rows.size());
        rows.forEach(row -> {
            for (int pos = 0; pos < row.size(); pos++) {
                columns.get(pos).add(row.get(pos));
            }
        });
        return new Grid(columns);
    }

    private ArrayList<ArrayList<String>> createColumnsOfSize(int size) {
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            columns.add(new ArrayList<>());
        }
        return columns;
    }

    public ArrayList<String> createBlock(ArrayList<ArrayList<String>> rows) {
        return rows.stream()
                .map(s -> s.subList(0, 3))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public <T extends ArrayList> T removeFirstThree(T rows) {
        return (T) new ArrayList<T>(rows.subList(3,rows.size()));
    }

    public ArrayList<ArrayList<String>> createBlocksOfRows(ArrayList<ArrayList<String>> rows) {
        ArrayList<ArrayList<String>> blocks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            blocks.add(createBlock(rows));
            rows = rows.stream().map(this::removeFirstThree)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return blocks;
    }


}