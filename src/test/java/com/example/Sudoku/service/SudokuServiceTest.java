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
        row1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        row2 = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "1"));
        row3 = new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8", "9", "1", "2"));
        row4 = new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9", "1", "2", "3"));
        row5 = new ArrayList<>(Arrays.asList("5", "6", "7", "8", "9", "1", "2", "3", "4"));
        row6 = new ArrayList<>(Arrays.asList("6", "7", "8", "9", "1", "2", "3", "4", "5"));
        row7 = new ArrayList<>(Arrays.asList("7", "8", "9", "1", "2", "3", "4", "5", "6"));
        row8 = new ArrayList<>(Arrays.asList("8", "9", "1", "2", "3", "4", "5", "6", "7"));
        row9 = new ArrayList<>(Arrays.asList("9", "1", "2", "3", "4", "5", "6", "7", "8"));
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
    public void shouldCreateBlockRowOfSize3() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ArrayList<String> block1 = new ArrayList<>(Arrays.asList("1", "2", "3", "2", "3", "4", "3", "4", "5"));

        assertEquals(block1, sudokuService.createBlock(rows)  );
    }

    @Test
    public void shouldCreateBlockRowOfSize31() {
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        ArrayList<ArrayList<String>> blocks = new ArrayList<>();
        blocks.add(new ArrayList<>(Arrays.asList("1", "2", "3", "2", "3", "4", "3", "4", "5")));
        blocks.add(new ArrayList<>(Arrays.asList("4", "5", "6", "5", "6", "7", "6", "7", "8")));
        blocks.add(new ArrayList<>(Arrays.asList("7", "8", "9", "8", "9", "1", "9", "1", "2")));

        assertEquals(blocks, sudokuService.createBlocksOfRows(rows));
    }

    @Test
    public void shouldRemoveFirstThreeElement() {
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
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "9"));

        assertEquals(expected, sudokuService.removeFirstThree(row1));
    }
}