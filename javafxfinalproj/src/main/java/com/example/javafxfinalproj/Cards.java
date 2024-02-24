package com.example.javafxfinalproj;

public class Cards {
    public String value;
    public int row;
    public int col;
    public boolean wasGuessed;

    Cards(String value,int row,int col){
        this.value = value;
        this.row = row;
        this.col = col;
        this.wasGuessed = false;
    }
}
