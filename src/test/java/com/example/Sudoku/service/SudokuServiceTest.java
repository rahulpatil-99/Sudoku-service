package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SudokuServiceTest {

    SudokuService sudokuService;
    ArrayList<ArrayList<String>> rows;

    @Before
    public void setUp() {
        sudokuService = new SudokuService();
        rows = new ArrayList<>();
    }

    @Test
    public void shouldReturnTrueWhenNoNumberIsRepeated() {
        ArrayList<String> row1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        ArrayList<String> row2 = new ArrayList<>(Arrays.asList("2", "3", "4"));
        ArrayList<String> row3 = new ArrayList<>(Arrays.asList("4", "1", "2"));
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        assertTrue(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldReturnFalseWhenNumberIsRepeated() {
        ArrayList<String> row = new ArrayList<>(Arrays.asList("1", "1", "3"));
        rows.add(row);
        assertFalse(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldReturnFalseWhenColumnHaveRepeatedNumber() {
        ArrayList<String> row = new ArrayList<>(Arrays.asList("1", "2"));
        rows.add(row);
        rows.add(row);
        assertFalse(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldTransposeGivenRowsIntoColumns() {
        ArrayList<String> row1 = new ArrayList<>(Arrays.asList("1", "2"));
        ArrayList<String> row2 = new ArrayList<>(Arrays.asList("3", "4"));
        rows.add(row1);
        rows.add(row2);
        ArrayList<String> col1 = new ArrayList<>(Arrays.asList("1", "3"));
        ArrayList<String> col2 = new ArrayList<>(Arrays.asList("2", "4"));
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(col1);
        columns.add(col2);
        ArrayList<ArrayList<String>> expected = sudokuService.transpose(new Grid(rows)).getDetails();
        assertEquals(columns, expected);
    }
}