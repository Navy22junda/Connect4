package com.example.connect4app.Connect4Logic;

public class Direction {

    /*DIRECTIONS*/

    public static final Direction DOWN = new Direction(1,0);

    public static final Direction RIGHT = new Direction(0,1);

    public static final Direction MAIN_DIGONAL = new Direction(1,1);

    public static final Direction CONTRA_DIAGONAL = new Direction(1, -1);

    /*DIRECTIONS ARRAY*/
    public static final Direction[] ALL = new Direction[]{
            DOWN, RIGHT, MAIN_DIGONAL, CONTRA_DIAGONAL
    };


    private final int changeInRow;
    private final int changeInColumn;

    public Direction(int changeInRow, int changeInColumn){

        this.changeInRow = changeInRow;
        this.changeInColumn = changeInColumn;

    }

    public int getChangeInRow(){
        return changeInRow;
    }

    public int getChangeInColumn(){
        return changeInColumn;
    }

    public Direction invert(){

        return new Direction(this.changeInRow * -1, this.changeInColumn * -1);
    }

}
