package com.example.sudokusolver.controllers;

import com.example.sudokusolver.models.Board;
import com.example.sudokusolver.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {


    private BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    @GetMapping
    public Board solveSudoku(@RequestBody Board board){
        return boardService.solveSudoku(board);
//            return null;
    }
}
