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
        int connected = 1;
        Player player = new Player(cells[position.getRow()][position.getColumn()].jugador);
        int maxNormal = maxConnectedNormal(maxConnected, connected, player, position);
        int maxReverse = maxConnectedReverse(maxConnected, connected, player, position);
        if(maxNormal > maxReverse){
            return maxNormal;
        }else {
            return maxReverse;
        }
    }

    private int maxConnectedNormal(int maxConnected, int connected, Player player, Position position){

        for (int i = 0; i < Direction.ALL.length; i++){
            Position current = position;
            connected = 1;
            while (current.move(Direction.ALL[i]).getColumn() < size && current.move(Direction.ALL[i]).getRow() < size &&
                    current.move(Direction.ALL[i]).getColumn() > 0 && current.move(Direction.ALL[i]).getRow() > 0){

                current = current.move(Direction.ALL[i]);
                if(cells[current.getRow()][current.getColumn()].jugador == player.id){
                    connected++;
                }else {
                    break;
                }
            }
            if(maxConnected < connected){
                maxConnected = connected;
            }
        }
        return maxConnected;
    }

    private int maxConnectedReverse(int maxConnected, int connected, Player player, Position position){

        for (int i = 0; i < Direction.ALL.length; i++){
            Position current = position;
            connected = 1;
            while (current.move(Direction.ALL[i].invert()).getColumn() < size && current.move(Direction.ALL[i].invert()).getRow() < size &&
                    current.move(Direction.ALL[i].invert()).getColumn() > 0 && current.move(Direction.ALL[i].invert()).getRow() > 0){

                current = current.move(Direction.ALL[i].invert());
                if(cells[current.getRow()][current.getColumn()].jugador == player.id){
                    connected++;
                }else {
                    break;
                }
            }
            if(maxConnected < connected){
                maxConnected = connected;
            }
        }
        return maxConnected;
    }
}
