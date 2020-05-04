package com.example.connect4app.Connect4Logic;

public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public Position move(Direction direction){
        Position newPosition = new Position(row + direction.getChangeInColumn(), column + direction.getChangeInRow());
        return newPosition;
    }

    public boolean isEqualTo(Position other){
        if(this.row == other.row && this.column == other.column){
            return true;
        }

        return false;
    }

    public static int pathLength(Position position1, Position position2){

        if(position1.row == position2.row){
            //Mateixa fila
            return Math.abs(position1.column - position2.column);

        }else if(position1.column == position2.column){
            //Mateixa columna
            return Math.abs(position1.row - position2.row);
        }else { //DIAGONAL
            return Math.abs(position1.column - position2.column);
        }
    }
}
