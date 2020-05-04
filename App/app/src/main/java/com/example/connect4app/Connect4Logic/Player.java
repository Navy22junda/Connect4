package com.example.connect4app.Connect4Logic;

class Player {


    int id;

    public Player(int id){

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Player player1(){
        return new Player(1);
    }

    public static Player player2(){
        return new Player(2);
    }

}
