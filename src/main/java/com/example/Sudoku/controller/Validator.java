package com.example.Sudoku.controller;

import com.example.Sudoku.domain.Grid;
import com.example.Sudoku.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Validator {

    private SudokuService sudokuService;

    @Autowired
    public Validator(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestBody Grid grid){
         return sudokuService.validate(grid);
    }
}
