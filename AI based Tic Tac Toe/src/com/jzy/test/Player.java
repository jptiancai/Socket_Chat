package com.jzy.test;

public abstract class Player {
    void playTurn(int pl,int turn){}
    void playerInit(){}
    void notifyWin(int pl){}
    void notifyLose(int pl){}
}