package com.jzy.test;

import java.util.ArrayList;

public class Layer {
    ArrayList<Node> nodes = new ArrayList<Node>();
    int layerNum = 0;
    public Layer(int Num){
        layerNum = Num;
    }
    
    public void refreshLayer(){
        for(int i=0;i<nodes.size();i++)
        {
            Node temp = nodes.get(i);
            if(temp.nodeType!=0)continue;
            temp.pref=0;
            for(int j=0;j<temp.sub_nodes.size();j++)
            {
                temp.pref += (int)(temp.sub_nodes.get(j).pref)/2;
            }
            //System.out.print(temp.pref!=0?temp.pref+"\n":"");
        }
    }
    
    public Node searchByState(int[][] state){
        
        String temp = ""+state[0][0]+state[0][1]+state[0][2]+state[1][0]+state[1][1]+state[1][2]+state[2][0]+state[2][1]+state[2][2];
        //System.out.print(temp.!=0?temp:"");
        Node ret = searchById(temp);
        return ret;
    }
    
    public Node searchById(String id){
        Node ret = null;
        
        for(int i=0;i<nodes.size();i++)
        {
            //System.out.println("*********"+nodes.get(i).id);
            if(nodes.get(i).id.equals(id))
            {
                ret = nodes.get(i);
                break;
            }
        }
            
        return ret;
    }
}