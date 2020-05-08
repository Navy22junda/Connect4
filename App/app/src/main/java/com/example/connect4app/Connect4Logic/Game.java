package com.example.connect4app.Connect4Logic;

import android.util.Log;

import java.util.Random;

public class Game {

    private final Board board;
    private final int toWin;
    private Status status;
    private boolean hasWinner;
    private Player turn;

    private int size;
    private int time;

    public Game(Board board, int size, int time, int toWin) {
        this.board = board;
        this.size = size;
        this.time = time;
        this.toWin = toWin;
        this.turn = Player.player1();
        this.status = Status.STATUS_PLAYING;
    }

    // Metode que fa jugar a la màquina
    public Position playOpponent () {
         //Juga de forma aleatoria
        Random r = new Random();
        int low = 0;
        int high = size;
        int result = r.nextInt(high-low) + low;
        //-1 a la primera fila
        while (board.firstEmptyRow(result) == -1){
            result = r.nextInt(high-low) + low;
        }
        return board.occupyCell(result, turn);
    }
    public void toggleTurn() {
        if(turn.id == 1){
            turn.id = 2;
        }else {
            turn.id = 1;
        }
    }
    void manageTime() {
         if(time < 0){
             status.state = 1;
         }

    }
    public boolean checkForFinish () {
         if(!board.hasValidMoves() || hasWinner || status.state == 1){
             return true;
         }
         return false;
    }

    //Jugada de persona
    public Position drop(int col){
        Position position = board.occupyCell(col, turn);
        if(board.maxConnected(position) >= 4){
            hasWinner = true;
        }
        return position;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
