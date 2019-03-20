package com.example.Sudoku.service;

import com.example.Sudoku.domain.Grid;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SudokuService {

    public Boolean validate(Grid grid) {
        return grid.getDetails()
                .stream()
                .anyMatch(s-> s.size() == new HashSet<>(s).size());

    }
}
