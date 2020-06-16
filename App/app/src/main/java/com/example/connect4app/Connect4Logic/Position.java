package com.example.connect4app.Connect4Logic;

import android.os.Parcel;
import android.os.Parcelable;

public class Position implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(row);
        dest.writeInt(column);
    }

    public static final Parcelable.Creator<Position> CREATOR = new Parcelable.Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel source) {
            return new Position(source.readInt(),source.readInt());
        }

        public Position[] newArray(int size) {
            return new Position[size];
        }
    };
}
