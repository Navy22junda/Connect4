package com.example.connect4app.Connect4Logic;

import androidx.annotation.Nullable;

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

    public boolean isEqualTo(Player other) {
        return (other != null) && this.id == other.id;
    }

}
