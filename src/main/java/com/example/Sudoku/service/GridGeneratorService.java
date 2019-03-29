package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class GridGeneratorService {

    private ValidatorService validatorService;

    @Autowired
    public GridGeneratorService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public Grid generate(int max) {
        Grid emptyGrid = fillWithSymbol(new Grid(validatorService.createEmptyGridOfSize(max)), max);
        ArrayList<String> list = createRangeTill(0, max-1);
        int count = 1;
        while(count < 31){

            ArrayList<String> range = createRangeTill(1, max);

            int rowNumber = Integer.parseInt(list.get((int) Math.floor(Math.random() * list.size())));
            int cellNumber = Integer.parseInt(list.get((int) Math.floor(Math.random() * list.size())));
            String element = range.get((int) Math.floor(Math.random() * range.size()));

            ArrayList<String> row = emptyGrid.getRows().get(rowNumber);

            if(row.get(cellNumber).equals("0")){
               row.set(cellNumber, element);

               if(validatorService.validate(emptyGrid)) count++;
               else row.set(cellNumber, "0");
            }
        }
        return emptyGrid;
    }

    public ArrayList<String> createRangeTill(int min, int max) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = min; i < max+1; i++) strings.add(String.valueOf(i));
        return strings;
    }

    private Grid fillWithSymbol(Grid emptyGrid, int max){
        emptyGrid.getRows().forEach(row -> {
            IntStream.range(0, max).mapToObj(i -> "0").forEachOrdered(row::add);
        });
        return emptyGrid;
    }
}
