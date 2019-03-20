package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuServiceTest {

    SudokuService sudokuService;
    ArrayList<String> row;

    @Before
    public void setUp() {
        sudokuService = new SudokuService();
        row = new ArrayList<>();
    }

    @Test
    public void shouldReturnTrueWhenNoNumberIsRepeated() {
        row.add("1");
        row.add("2");
        row.add("3");
        Boolean expectedValidation = sudokuService.validate(new Grid(row));
        assertTrue(expectedValidation);
    }

    @Test
    public void shouldReturnFalseWhenNumberIsRepeated() {
        row.add("1");
        row.add("1");
        row.add("3");
        Boolean expectedValidation = sudokuService.validate(new Grid(row));
        assertFalse(expectedValidation);
    }


}