package com.example.Sudoku.controller;

import com.example.Sudoku.domain.Grid;
import com.example.Sudoku.service.GridGeneratorService;
import com.example.Sudoku.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Validator {

    private ValidatorService validatorService;
    private GridGeneratorService gridGeneratorService;

    @Autowired
    public Validator(ValidatorService validatorService, GridGeneratorService gridGeneratorService) {
        this.validatorService = validatorService;
        this.gridGeneratorService = gridGeneratorService;
    }

    @PostMapping("/validate")
    public Boolean validate(@RequestBody Grid grid){
         return validatorService.validate(grid);
    }

    @GetMapping("/")
    public Grid generate(){
        return gridGeneratorService.generate(9);
    }
}
