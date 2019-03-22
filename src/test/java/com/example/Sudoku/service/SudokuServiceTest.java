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
    ArrayList<String> row1, row2, row3, row4,row5, row6, row7, row8, row9;

    @Before
    public void setUp() {
        sudokuService = new SudokuService();
        rows = new ArrayList<>();
        row1 = new ArrayList<>(Arrays.asList("7", "3", "5", "6", "1", "4", "8", "9", "2"));
        row2 = new ArrayList<>(Arrays.asList("8", "4", "2", "9", "7", "3", "5", "6", "1"));
        row3 = new ArrayList<>(Arrays.asList("9", "6", "1", "2", "8", "5", "3", "7", "4"));
        row4 = new ArrayList<>(Arrays.asList("2", "8", "6", "3", "4", "9", "1", "5", "7"));
        row5 = new ArrayList<>(Arrays.asList("4", "1", "3", "8", "5", "7", "9", "2", "6"));
        row6 = new ArrayList<>(Arrays.asList("5", "7", "9", "1", "2", "6", "4", "3", "8"));
        row7 = new ArrayList<>(Arrays.asList("1", "5", "7", "4", "9", "2", "6", "8", "3"));
        row8 = new ArrayList<>(Arrays.asList("6", "9", "4", "7", "3", "8", "2", "1", "5"));
        row9 = new ArrayList<>(Arrays.asList("3", "2", "8", "5", "6", "1", "7", "4", "9"));
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
        ArrayList<ArrayList<String>> expected = sudokuService.transpose(rows);

        assertEquals(columns, expected);
    }

    @Test
    public void shouldReturnTrueIfAllValidationsAreSatisfied() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);
        assertTrue(sudokuService.validate(new Grid(rows)));
    }

    @Test
    public void shouldCreateSingleBlock() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ArrayList<String> block1 = new ArrayList<>(Arrays.asList("7", "3", "5", "8", "4", "2", "9", "6", "1"));

        assertEquals(block1, sudokuService.createBlock(rows));
    }

    @Test
    public void shouldCreateBlocksFromGivenRows() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ArrayList<ArrayList<String>> blocks = new ArrayList<>();
        blocks.add(new ArrayList<>(Arrays.asList("7", "3", "5", "8", "4", "2", "9", "6", "1")));
        blocks.add(new ArrayList<>(Arrays.asList("6", "1", "4", "9", "7", "3", "2", "8", "5")));
        blocks.add(new ArrayList<>(Arrays.asList("8", "9", "2", "5", "6", "1", "3", "7", "4")));

        assertEquals(blocks, sudokuService.createBlocksOfRows(rows));
    }

    @Test
    public void shouldRemoveFirstThreeRowsFromTheGrid() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);

        ArrayList<ArrayList<String>> expectedList = new ArrayList<>();
        expectedList.add(row4);
        expectedList.add(row5);
        expectedList.add(row6);

        assertEquals(expectedList, sudokuService.removeFirstThree(rows));
    }


    @Test
    public void shouldRemoveFirstThreeElementsFromTheRow() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("6", "1", "4", "8", "9", "2"));

        assertEquals(expected, sudokuService.removeFirstThree(row1));
    }

    @Test
    public void shouldCreateBlocksFromTheGrid() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);

        ArrayList<ArrayList<String>> expectedList = new ArrayList<>();
        expectedList.add(new ArrayList<>(Arrays.asList("7", "3", "5", "8", "4", "2", "9", "6", "1")));
        expectedList.add(new ArrayList<>(Arrays.asList("6", "1", "4", "9", "7", "3", "2", "8", "5")));
        expectedList.add(new ArrayList<>(Arrays.asList("8", "9", "2", "5", "6", "1", "3", "7", "4")));

        expectedList.add(new ArrayList<>(Arrays.asList("2", "8", "6", "4", "1", "3", "5", "7", "9")));
        expectedList.add(new ArrayList<>(Arrays.asList("3", "4", "9", "8", "5", "7", "1", "2", "6")));
        expectedList.add(new ArrayList<>(Arrays.asList("1", "5", "7", "9", "2", "6", "4", "3", "8")));

        expectedList.add(new ArrayList<>(Arrays.asList("1", "5", "7", "6", "9", "4", "3", "2", "8")));
        expectedList.add(new ArrayList<>(Arrays.asList("4", "9", "2", "7", "3", "8", "5", "6", "1")));
        expectedList.add(new ArrayList<>(Arrays.asList("6", "8", "3", "2", "1", "5", "7", "4", "9")));

        assertEquals(expectedList, sudokuService.createBlocksFromGrid(new Grid(rows)));
    }
}