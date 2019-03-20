package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuServiceTest {

    SudokuService sudokuService;
    ArrayList<ArrayList<String>> row;

    @Before
    public void setUp() {
        sudokuService = new SudokuService();
        row = new ArrayList<>();
    }

    @Test
    public void shouldReturnTrueWhenNoNumberIsRepeated() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "2", "3"));
        row.add(list);
        row.add(list);
        row.add(list);
        Boolean expectedValidation = sudokuService.validate(new Grid(row));
        assertTrue(expectedValidation);
    }

    @Test
    public void shouldReturnFalseWhenNumberIsRepeated() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "1", "3"));
        row.add(list);
        Boolean expectedValidation = sudokuService.validate(new Grid(row));
        assertFalse(expectedValidation);
    }


}