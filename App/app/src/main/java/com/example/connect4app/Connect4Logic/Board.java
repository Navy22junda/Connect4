package com.example.connect4app.Connect4Logic;

import android.util.Log;

public class Board {
    private final int size;
    private final Cell[][] cells;

    public Board(int size, Cell[][] cells){
        this.size = size;
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Position occupyCell(int column, Player player){

        if(hasValidMoves() && column < size){
            int row = firstEmptyRow(column);
            cells[row][column].jugador = player.id;
            cells[row][column].ocupat = true;
            return new Position(row,column);
        }

        return null;
    }

    public boolean hasValidMoves(){
        for(int i = 0; i < size; i++){
            if(!cells[i][0].ocupat){
                return true;
            }
        }
        return false;
    }

    public int firstEmptyRow(int column){
        if(column <= size - 1) {
            for (int i = 0; i < size; i++) {
                if (!cells[i][column].ocupat) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int maxConnected(Position position){
        int maxConnected = 0;
        Player player = new Player(cells[position.getRow()][position.getColumn()].jugador);

        for (int i = 0; i < Direction.ALL.length; i++) {
            int connected = 1;
            connected += maxConnectedNormal(position, i, player);
            connected += maxConnectedReverse(position, i, player);
            if(maxConnected < connected) {
                maxConnected = connected;
            }
        }
        return maxConnected;
    }

    private int maxConnectedNormal(Position currentNormal, int i, Player player) {
        int connected = 0;
        while (currentNormal.move(Direction.ALL[i]).getColumn() < size && currentNormal.move(Direction.ALL[i]).getRow() < size &&
                currentNormal.move(Direction.ALL[i]).getColumn() >= 0 && currentNormal.move(Direction.ALL[i]).getRow() >= 0) {

            currentNormal = currentNormal.move(Direction.ALL[i]);
            if(cells[currentNormal.getRow()][currentNormal.getColumn()].jugador == player.id) {
                connected++;
            }else {
                break;
            }
        }
        return connected;
    }

    private int maxConnectedReverse(Position currentReverse, int i,Player player){
        int connected = 0;
        while (currentReverse.move(Direction.ALL[i].invert()).getColumn() < size && currentReverse.move(Direction.ALL[i].invert()).getRow() < size &&
                currentReverse.move(Direction.ALL[i].invert()).getColumn() >= 0 && currentReverse.move(Direction.ALL[i].invert()).getRow() >= 0){
            currentReverse = currentReverse.move(Direction.ALL[i].invert());

            if(cells[currentReverse.getRow()][currentReverse.getColumn()].jugador == player.id) {
                connected++;
            }else {
                break;
            }
        }
        return connected;
    }
}
