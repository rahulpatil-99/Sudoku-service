package com.example.Sudoku.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class GridGeneratorServiceTest {
    private ValidatorService validatorService;
    private GridGeneratorService gridGeneratorService;

    @Before
    public void setUp() {
        validatorService = new ValidatorService();
        gridGeneratorService = new GridGeneratorService(validatorService);
    }

    @Test
    public void shouldCreateRangeForGivenNumbers() {
        ArrayList<String> range = gridGeneratorService.createRangeTill(1, 4);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");

        assertEquals(expected, range);
    }
}
