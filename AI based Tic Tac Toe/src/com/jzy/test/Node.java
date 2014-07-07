package com.jzy.test;

import java.util.ArrayList;

public class Node {
    ArrayList<Node> sub_nodes = new ArrayList<Node>();
    String subNodes="";
    String id="";
    int pref = 0;
    int n=0;
    int lNum;
    Computer comp;
    int nodeType=0;
    public Node(String i,int l,Computer c){
        id=i;
        lNum = l;
        comp = c;
    }
    
    public void setAsState(){
        for(int i=0;i<id.length();i++)
        {
            int val = (int)(id.charAt(i)-'0');
            int t = i/3;
            TicTacToe.state[t][i-(t*3)] = val;
            
        }
    }
    
    public void playNext1(){
        ArrayList<Node> temp = new ArrayList<Node>();
        //System.out.println(id+" : "+sub_nodes.size());
        long max = sub_nodes.get(0).pref;
            for(int i=0;i<sub_nodes.size();i++)
            {
                if(sub_nodes.get(i).pref > max)
                {
                    temp.clear();
                    temp.add(sub_nodes.get(i));
                    max = sub_nodes.get(i).pref;
                }
                else if(sub_nodes.get(i).pref == max)
                {
                    temp.add(sub_nodes.get(i));
                }
            }
        
        int choice = (int) (Math.random()*temp.size());
        temp.get(choice).n++;
        temp.get(choice).setAsState();
    }
    
    public void playNext2(){
        ArrayList<Node> temp = new ArrayList<Node>();
        //System.out.println(id+" : "+sub_nodes.size());
        long min = sub_nodes.get(0).pref;
            for(int i=0;i<sub_nodes.size();i++)
            {
                if(sub_nodes.get(i).pref < min)
                {
                    temp.clear();
                    temp.add(sub_nodes.get(i));
                    min = sub_nodes.get(i).pref;
                }
                else if(sub_nodes.get(i).pref == min)
                {
                    temp.add(sub_nodes.get(i));
                }
            }
        
        int choice = (int) (Math.random()*temp.size());
        temp.get(choice).n++;
        temp.get(choice).setAsState();
    }
    
    public void playNextn(){
        ArrayList<Node> temp = new ArrayList<Node>();
        //System.out.println(id+" : "+sub_nodes.size());
        int choice = 0;
        long min = sub_nodes.get(0).n;
            for(int i=0;i<sub_nodes.size();i++)
            {
                if(sub_nodes.get(i).n < min)
                {
                    min = sub_nodes.get(i).n;
                    choice = i;
                }
            }
        
        sub_nodes.get(choice).n++;
        sub_nodes.get(choice).setAsState();
    }
    
    public void extractNodes(){
        if(lNum!=9)
        {
            int l = subNodes.length();
            String w="";
            for(int i=0;i<l;i++)
            {
                char ch = subNodes.charAt(i);
                if(ch!=',')
                {
                    w+=ch;
                    
                }
                else
                {
                    sub_nodes.add(comp.layers.get(lNum).searchById(w));
                    w="";
                }
            }
        }
        
    }
}
