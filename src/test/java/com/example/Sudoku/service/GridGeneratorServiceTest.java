package com.example.Sudoku.service;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GridGeneratorServiceTest {

    @Test
    public void shouldGenerateGridOfUniqueElementsRows() {
        GridGeneratorService service = new GridGeneratorService(new ValidatorService());
        ArrayList<ArrayList<String>> grid = service.generate(9);
        assertEquals(grid.size(), 9);
    }
}