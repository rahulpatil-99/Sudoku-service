package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ValidatorService {

    public Boolean validate(Grid grid) {
        return DoesHaveUniqueElements(grid.getRows()) &&
                DoesHaveUniqueElements(transpose(grid.getRows())) &&
                DoesHaveUniqueElements(createBlocksFromGrid(grid.getRows()));
    }

    public boolean DoesHaveUniqueElements(ArrayList<ArrayList<String>> rows){
        ArrayList<ArrayList<String>> uniqueElementsArray = new ArrayList<>();
        rows.forEach(row -> {
            ArrayList<String> arrayList = new ArrayList<>();
            row.stream().filter(s -> !arrayList.contains(s) || s.equals("0")).forEach(arrayList::add);
            uniqueElementsArray.add(arrayList);
        });
        return uniqueElementsArray.containsAll(rows);
    }

    public ArrayList<ArrayList<String>> transpose(ArrayList<ArrayList<String>> rows) {
        ArrayList<ArrayList<String>> columns = createEmptyGridOfSize(rows.size());
        rows.forEach(row -> IntStream.range(0, row.size()).forEach(pos -> columns.get(pos).add(row.get(pos))));
        return columns;
    }

    public ArrayList<ArrayList<String>> createEmptyGridOfSize(int size) {
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


    public ArrayList<ArrayList<String>> createBlocksFromGrid(ArrayList<ArrayList<String>> rows) {
        ArrayList<ArrayList<String>> blocks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            blocks.addAll(createBlocksOfRows(new ArrayList<>(rows.subList(0, 3))));
            rows = removeFirstThree(rows);
        }
        return blocks;
    }
}