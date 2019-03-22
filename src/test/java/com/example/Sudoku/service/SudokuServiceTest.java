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
        rows.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        rows.add(new ArrayList<>(Arrays.asList("2", "3", "4")));
        rows.add(new ArrayList<>(Arrays.asList("4", "1", "2")));

        assertTrue(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldReturnFalseWhenNumberIsRepeated() {
        rows.add(new ArrayList<>(Arrays.asList("1", "1", "3")));

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
        rows.add(new ArrayList<>(Arrays.asList("1", "2")));
        rows.add(new ArrayList<>(Arrays.asList("3", "4")));

        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(new ArrayList<>(Arrays.asList("1", "3")));
        columns.add(new ArrayList<>(Arrays.asList("2", "4")));
        ArrayList<ArrayList<String>> expected = sudokuService.transpose(new Grid(rows)).getDetails();

        assertEquals(columns, expected);
    }


    @Test
    public void solveSudukoGrid() {
        rows.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        rows.add(new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "1")));
        rows.add(new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "1", "2")));
        rows.add(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9", "1", "2", "3")));
        rows.add(new ArrayList<>(Arrays.asList("5", "6", "7", "8", "9", "1", "2", "3", "4")));
        rows.add(new ArrayList<>(Arrays.asList("6", "7", "8", "9", "1", "2", "3", "4", "5")));
        rows.add(new ArrayList<>(Arrays.asList("7", "8", "9", "1", "2", "3", "4", "5", "6")));
        rows.add(new ArrayList<>(Arrays.asList("8", "9", "1", "2", "3", "4", "5", "6", "7")));
        rows.add(new ArrayList<>(Arrays.asList("9", "1", "2", "3", "4", "5", "6", "7", "8")));
        assertTrue(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldCreateBlockRowOfSize3() {
        rows.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        rows.add(new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "1")));
        rows.add(new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "1", "2")));

        ArrayList<String> block1 = new ArrayList<>(Arrays.asList("1", "2", "3", "2", "3", "4", "3", "4", "5"));

        assertEquals(block1, sudokuService.createBlock(new Grid(rows)));
    }

    @Test
    public void shouldCreateBlockRowOfSize31() {
        rows.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        rows.add(new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "1")));
        rows.add(new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "1", "2")));

        ArrayList<ArrayList<String>> blocks = new ArrayList<>();
        blocks.add(new ArrayList<>(Arrays.asList("1", "2", "3", "2", "3", "4", "3", "4", "5")));
        blocks.add(new ArrayList<>(Arrays.asList("4", "5", "6", "5", "6", "7", "6", "7", "8")));
        blocks.add(new ArrayList<>(Arrays.asList("7", "8", "9", "8", "9", "1", "9", "1", "2")));

        assertEquals(new ArrayList<>(), sudokuService.createBlocksForRows(new Grid(rows)));
    }

    @Test
    public void shouldRemoveFirstThreeElement() {
        rows.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        rows.add(new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "1")));
        rows.add(new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "1", "2")));
        rows.add(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9", "1", "2", "3")));
        rows.add(new ArrayList<>(Arrays.asList("5", "6", "7", "8", "9", "1", "2", "3", "4")));
        rows.add(new ArrayList<>(Arrays.asList("6", "7", "8", "9", "1", "2", "3", "4", "5")));

        ArrayList<ArrayList<String>> expectedList = new ArrayList<>();
        expectedList.add(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9", "1", "2", "3")));
        expectedList.add(new ArrayList<>(Arrays.asList("5", "6", "7", "8", "9", "1", "2", "3", "4")));
        expectedList.add(new ArrayList<>(Arrays.asList("6", "7", "8", "9", "1", "2", "3", "4", "5")));

        assertEquals(expectedList, sudokuService.removeFirstThree(rows));
    }


    @Test
    public void shouldRemoveFirstThreeElementsFromTheRow() {
        ArrayList<String> row = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

        ArrayList<String> expected = new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9"));

        assertEquals(expected, sudokuService.removeFirstThree(row));
    }
}