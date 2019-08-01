package com.basak.tictactoe;

public class Board {
    private static byte EMPTY =0;
    private static byte PLAYER_1_SYMBOL = 1;
    private static byte PLAYER_2_SYMBOL = 2;
    private boolean player1Turn = true;
    byte [][] board = new byte [3][3];
    BoardListener boardListener;
    byte moveCount;
    public Board(BoardListener listener) {
        boardListener = listener;
    }
    public void move(byte row, byte col) {
        if(board[row][col] != EMPTY )
            return;
        if (player1Turn){
            board[row][col] = PLAYER_1_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_1,row,col);
        }else{
            board[row][col] = PLAYER_2_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_2,row,col);
        }
        moveCount++;
        if(checkBoard((byte)(player1Turn? 1 : 2))){
            boardListener.gameEnded((byte)(player1Turn? BoardListener.PLAYER_1 : BoardPresenter.PLAYER_2));
            return;
        }

        if(moveCount ==9){
            boardListener.gameEnded(BoardListener.NO_ONE);
        }

        player1Turn = !player1Turn;
    }

    private boolean checkBoard(byte symbol) {
        for(byte row =0;row<3;row++){
            boolean win = true;
            for(byte col =0;col<3;col++){
                win = win && board[row][col] == symbol;
            }
            if(win){
                return true;
            }
        }
        return false;
    }
}