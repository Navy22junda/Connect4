package com.example.connect4app.Connect4Logic;

class Status {

    int state;

    public static final Status STATUS_PLAYING = new Status(0);
    public static final Status STATUS_FINAL = new Status(1);



    public Status(int state){
        this.state = state;
    }
}
