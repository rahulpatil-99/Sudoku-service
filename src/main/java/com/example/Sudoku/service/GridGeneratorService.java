package com.example.Sudoku.service;

import java.util.ArrayList;
import java.util.Random;

public class GridGeneratorService {

    private ValidatorService validatorService;

    public GridGeneratorService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public ArrayList<ArrayList<String>> generate(int max) {
        ArrayList<ArrayList<String>> emptyGrid = fillWithSymbol(validatorService.createEmptyGridOfSize(max));
        Random random = new Random();
        emptyGrid.forEach(row -> {
            ArrayList<String> range = createRangeTill(max);
            for(int i = 0; i < max; i++) {
                if(!range.isEmpty()){
                    String element = range.get(random.nextInt(range.size()));
                    row.set(i, element);
                    range.remove(element);
                }
            }
        });
        return emptyGrid;
    }

    private ArrayList<String> createRangeTill(int max) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i < max+1; i++) {
            strings.add(String.valueOf(i));
        }
        return strings;
    }

    private ArrayList<ArrayList<String>> fillWithSymbol(ArrayList<ArrayList<String>> emptyGrid){
        emptyGrid.forEach(row -> {
            for(int i = 0; i < 9; i++) row.add("_");
        });
        return emptyGrid;
    }
}
