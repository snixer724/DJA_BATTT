package com.example.tictactoedemo;

/**
 * Created by Drew on 2/16/14.
 */

public enum Player {
    E ("-"), X ("x"), O ("o");

    private String type;

    Player(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
