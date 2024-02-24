package com.example.javafxfinalproj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameBoard {
    public static int rowNum;
    public static int colNum;
    public Cards[][] Cells = new Cards[rowNum][colNum];
//    private final ArrayList<String> memoryOp = new ArrayList<>(Arrays.asList("apple", "circle", "heart", "diamond", "clover", "diamond","apple", "circle", "heart", "diamond", "clover", "diamond"));
//    String[] memoryOp = {"apple", "circle", "heart", "diamond", "clover", "diamond","apple", "circle", "heart", "diamond", "clover", "diamond"};
    String[] memoryOp = {"Beaver", "Cheetah", "Chicken", "Duck", "Fox", "goose","Horse", "lion", "Panda", "Parrot", "Rabbit", "Snail", "Swan", "Toco", "Tortoise", "Cheetah2", "Parrot2", "Turtles"
        ,"Unknown","Pelican","Bear","Panther","Cat2","Monkey","Camel2","Goat","Emu","Owl","Deer","Eagle","Camel","Cat"};

    public void populateBoard(){
        System.out.println(rowNum);
        Cells = new Cards[rowNum][colNum];
        Random randomImage = new Random();

        int randomRow1;
        int randomCol1;
        int randomRow2;
        int randomCol2;
        for (int i = 0; i < (rowNum*colNum)/2; i++) {
//            String memoryOption = memoryOp.get(i);
//            int randomImageIndex = randomImage.nextInt(memoryOp.length);
            String randomImageSelected = memoryOp[i];
            //labo labo oo sawir ugalina
            do {
                randomRow1 = randomImage.nextInt(rowNum);
                randomCol1 = randomImage.nextInt(colNum);
            } while (Cells[randomRow1][randomCol1] != null);

            do {
                randomRow2 = randomImage.nextInt(rowNum);
                randomCol2 = randomImage.nextInt(colNum);
            } while ((randomRow1 == randomRow2 && randomCol1 == randomCol2) || Cells[randomRow2][randomCol2] != null);


            Cells[randomRow1][randomCol1] = new Cards(randomImageSelected, randomRow1, randomCol1);
            Cells[randomRow2][randomCol2] = new Cards(randomImageSelected, randomRow2, randomCol2);
            System.out.println(Cells[randomRow1][randomCol1].value+randomRow1+","+randomCol1);
            System.out.println(Cells[randomRow2][randomCol2].value+randomRow2+","+randomCol2);
        }


    }



    void BoardSize(int row, int col) {
        rowNum = row;
        colNum = col;
    }
}
