package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    public ArrayList<String> createBlock(Grid grid) {
        ArrayList<ArrayList<String>> firstThree = new ArrayList<>(grid.getDetails().subList(0, 3));
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        firstThree.forEach(row -> newList.add(new ArrayList<>(row.subList(0,3))));

        return newList.stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> createBlocksForRows(Grid grid) {
        return new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> removeFirstThree(ArrayList<ArrayList<String>> rows) {
        return new ArrayList<>(rows.subList(3,rows.size()));

    }
}