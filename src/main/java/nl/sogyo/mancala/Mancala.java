package nl.sogyo.mancala;

import java.util.Scanner;

public class Mancala
{
    static RegularBox mancala = new RegularBox(4,14);
    static Scanner scanner = new Scanner(System.in);
    static int userInput;

    public static void main( String[] args ) {
        printBoard();
        while(mancala.player.canPlay()){
            askUserInput();
            mancala.playBox(userInput);
            printBoard();
        }
        int[] score = mancala.player.getScore();
        System.out.println("Final score: p1 has " + score[0] + " points & player 2 has " + score[1] + " points." );
    }

    public static void printBoard(){
        int[] board = new int[14];
        for (int i = 0; i<board.length; i++){
            board[i] = mancala.getStoneAmountNextBox(i);
        }
        System.out.println("    " + board[12] + " " + board[11] + " " + board[10] + " " + board[9] + " " + board[8] + " " + board[7]);
        System.out.println("[" + board[13] + "]             [" + board[6] + "]");
        System.out.println("    " + board[0] + " " + board[1] + " " + board[2] + " " + board[3] + " " + board[4] + " " + board[5]);
    }

    public static int askUserInput(){
        System.out.println("");
        if(mancala.player.getIsActivePlayer()){
            System.out.print("Player 1, make your move >> ");
        }
        else{
            System.out.print("Player 2, make your move >> ");
        }
        userInput = scanner.nextInt();
        if(!mancala.player.getIsActivePlayer()){
            userInput += 7;
        }
        System.out.println("");
        return userInput;
    }
}
