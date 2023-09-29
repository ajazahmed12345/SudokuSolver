package com.example.sudokusolver.services;

import com.example.sudokusolver.models.Board;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BoardService {

    List<List<Integer>> ans = new ArrayList<>();  // to-store the input
    public Board solveSudoku(Board board){
        List<List<String>> matrix = board.getMatrix();

        char[][] A = new char[9][9];
        int i, j;

        for(i=0; i < 9; i++){

            for(j=0; j < 9; j++){

                char val = (char)(Integer.parseInt(matrix.get(i).get(j))+'0');

                A[i][j] = val;
            }

        }


        sudoku(A);

        List<List<String>> ansMatrix = new ArrayList<>();

        for(i=0; i < 9; i++){
            List<String> temp = new ArrayList<>();
            for(j=0; j < 9; j++){
                Character currVal = (char)(ans.get(i).get(j) + '0');
                temp.add(currVal.toString());
            }

            ansMatrix.add(temp);
        }

        Board b = new Board();
        b.setMatrix(ansMatrix);

        return b;


    }



    public void sudoku(char[][] A){
        int i, j;

        fun(0, A);

        for(i=0; i < 9; i++){
            for(j=0; j < 9; j++){
                System.out.print(ans.get(i).get(j) + " ");
            }

            System.out.println();
        }

    }



    boolean isSafe(int ind, char val, char[][] A){
        int i, j;

        boolean f = true;

        int row = ind/9;
        int col = ind%9;

        for(j=0; j < 9; j++){
            if(A[row][j] == val){
                return false;
            }
        }

        for(i=0; i < 9; i++){
            if(A[i][col] == val){
                return false;
            }
        }

        int stRow = (row/3)*3;
        int stCol = (col/3)*3;

        for(i=stRow; i < stRow+3; i++){
            for(j=stCol; j < stCol+3; j++){
                if(A[i][j] == val){
                    return false;
                }
            }
        }

        return true;
    }

    public void fun(int ind, char[][] A){

        if(ind == 81){
            for(int k = 0; k < 9; k++){
                List<Integer> temp = new ArrayList<>();
                for(int l = 0; l < 9; l++){

                    temp.add((int)(A[k][l]-'0'));
                }
                ans.add(temp);
            }
            return;
        }

        int i, j;

        int row = ind/9;
        int col = ind%9;

        if(A[row][col] != '0'){
            fun(ind+1, A);
        }
        else{
            for(char val = '1'; val <= '9'; val++){
                if(isSafe(ind, val, A)) {
                    A[row][col] = val;
                    fun(ind+1, A);
                    A[row][col] = '0';  // back-track
                }
            }
        }


    }

}
