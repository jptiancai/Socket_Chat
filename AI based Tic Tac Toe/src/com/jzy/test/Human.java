package com.jzy.test;

public class Human extends Player {
    @Override
    void playTurn(int pl,int turn){
        while(TicTacToe.butClicked == 0);
        switch(TicTacToe.butClicked)
        {
            case 1 : TicTacToe.state[0][0]=pl;break;
            case 2 : TicTacToe.state[0][1]=pl;break;
            case 3 : TicTacToe.state[0][2]=pl;break;
            case 4 : TicTacToe.state[1][0]=pl;break;
            case 5 : TicTacToe.state[1][1]=pl;break;
            case 6 : TicTacToe.state[1][2]=pl;break;
            case 7 : TicTacToe.state[2][0]=pl;break;
            case 8 : TicTacToe.state[2][1]=pl;break;
            case 9 : TicTacToe.state[2][2]=pl;break;
        }
        TicTacToe.butClicked = 0;
    }
}
