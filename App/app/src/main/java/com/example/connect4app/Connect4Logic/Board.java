package com.example.connect4app.Connect4Logic;

public class Board {
    private final int size;
    private final Cell[][] cells;

    Board(int size, Cell[][] cells){
        this.size = size;
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Position occupyCell(int column, Player player){

        if(hasValidMoves()){
            cells[firstEmptyRow(column)][column].jugador = player.id;
            return new Position(firstEmptyRow(column),column);
        }

        return null;
    }

    public boolean hasValidMoves(){
        for(int i = 0; i < size; i++){
            if(cells[size][i] == null){
                return true;
            }
        }
        return false;
    }

    public int firstEmptyRow(int column){
        if(column <= size) {
            for (int i = size; i > 0; i--) {
                if (cells[i][column].ocupat != true) {
                    cells[i][column].ocupat = true;
                    return i;
                }
            }
        }
        return -1;
    }

    public int maxConnected(Position position){
        int max = 1;
        int maxConnected = 0;
        Player player = new Player(cells[position.getRow()][position.getColumn()].jugador);
        for (int i = 0; i < Direction.ALL.length; i++){
            max = 1;
            while (position.move(Direction.ALL[i]).getColumn() < size && position.move(Direction.ALL[i]).getRow() < size){
                    Position current = position.move(Direction.ALL[i]);
                    if(cells[current.getRow()][current.getColumn()].jugador == player.id){
                        max++;
                    }else {
                        break;
                    }
                }
            }
        if(maxConnected < max){
            maxConnected = max;
        }
        return maxConnected;
    }
}
