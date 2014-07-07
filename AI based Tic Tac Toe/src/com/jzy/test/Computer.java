package com.jzy.test;

import java.io.*;
import java.util.ArrayList;


public class Computer extends Player {
    int t=0;
    Node begin = new Node("000000000",0,this);
    Node current = begin;
    
        double lr = 0;
    File[] layerFiles = new File[9];
    ArrayList<Layer> layers = new ArrayList<Layer>();
    public Computer(String l){
        for(int i=0;i<9;i++)
        {
            layerFiles[i] = new File(l+(i+1)+".nodes");
            if(!layerFiles[i].exists())
            {
                try{
                    layerFiles[i].createNewFile();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
        //loadMind();
        makeNewMind();evaluate();saveMind();
        begin.subNodes="100000000,010000000,001000000,000100000,000010000,000001000,000000100,000000010,000000001,";
        begin.extractNodes();
    }
    public void evaluate(){
        for(int i=0;i<9;i++)
        {
            Layer l = layers.get(i);
            for(int j=0;j<l.nodes.size();j++)
            {
                String x="" , o="" , s = l.nodes.get(j).id;
                for(int k=0;k<9;k++)
                {
                    char ch = s.charAt(k);
                    if(ch=='1')
                    {
                        x+=(k+1);
                    }
                    else if(ch=='2')
                    {
                        o+=(k+1);
                    }
                }
                int r = TicTacToe.checkWin2(x,o);
                switch(r)
                {
                    case 1 : l.nodes.get(j).pref=50;
                        l.nodes.get(j).nodeType=1;
                        break;
                    case 2 : l.nodes.get(j).pref=-50;
                        l.nodes.get(j).nodeType=1;
                        break;
                    case -1 : 
                        l.nodes.get(j).nodeType=1;
                        break;
                }
            }
        }
    }
    public void loadMind(){
        FileReader f;
        BufferedReader r;
        int i=0;
        try{
            for(int l=0;l<9;l++)
            {
                layers.add(new Layer(l+1));
                f = new FileReader(layerFiles[l]);
                r = new BufferedReader(f);
                String line;
                while((line=r.readLine())!=null)
                {
                    Node temp = new Node(r.readLine(),l+1,this);
                    temp.subNodes = r.readLine();
                    String no = r.readLine();
                    //System.out.println(no);
                    temp.pref = Integer.parseInt(no);
                    temp.n = Integer.parseInt(r.readLine());
                    temp.nodeType = Integer.parseInt(r.readLine());
                    layers.get(l).nodes.add(temp);
                }
            }
            
            for(int l=0;l<9;l++)
            {
                for(int j=0;j<layers.get(l).nodes.size();j++)
                {
                    layers.get(l).nodes.get(j).extractNodes();
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    public void saveMind(){
        for(int i=7;i>=0;i--)
        {
            layers.get(i).refreshLayer();
        }
        try{
            for(int i=0;i<9;i++)
            {
                Layer l = layers.get(i);
                PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(layerFiles[i])));
                for(int j=0;j<l.nodes.size();j++)
                {
                    Node temp = l.nodes.get(j);
                    p.println("***********************************************");
                    p.println(temp.id);
                    //System.out.println(temp.id);
                    String s=""; 
                    for(int k=0;k<temp.sub_nodes.size();k++)
                    {
                        s+=temp.sub_nodes.get(k).id+",";
                    }
                    p.println(s);
                    p.println(temp.pref);
                    p.println(temp.n);
                    p.println(temp.nodeType);
                }
                p.close();
                //System.out.println("layer "+i+" : "+l.nodes.size());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
    public void makeNewMind(){
        layers.add(new Layer(1));layers.add(new Layer(2));layers.add(new Layer(3));layers.add(new Layer(4));layers.add(new Layer(5));layers.add(new Layer(6));layers.add(new Layer(7));layers.add(new Layer(8));layers.add(new Layer(9));
        
        for(int i1=0;i1<=2;i1++){
        for(int i2=0;i2<=2;i2++){
        for(int i3=0;i3<=2;i3++){
        for(int i4=0;i4<=2;i4++){
        for(int i5=0;i5<=2;i5++){
        for(int i6=0;i6<=2;i6++){
        for(int i7=0;i7<=2;i7++){
        for(int i8=0;i8<=2;i8++){
        for(int i9=0;i9<=2;i9++){
            int l=9;
            if(i1==0)l--;
            if(i2==0)l--;
            if(i3==0)l--;
            if(i4==0)l--;
            if(i5==0)l--;
            if(i6==0)l--;
            if(i7==0)l--;
            if(i8==0)l--;
            if(i9==0)l--;
            
            int x=0;
            if(i1==1)x++;
            if(i2==1)x++;
            if(i3==1)x++;
            if(i4==1)x++;
            if(i5==1)x++;
            if(i6==1)x++;
            if(i7==1)x++;
            if(i8==1)x++;
            if(i9==1)x++;
            
            int o = l-x;
            
            if((x-o==0 || x-o==1) && l!=0 )
            {
                String id = ""+i1+i2+i3+i4+i5+i6+i7+i8+i9;
                layers.get(l-1).nodes.add(new Node(id,l,this));
            }
        }}}}}}}}} 
        for(int l=1;l<9;l++)
        {
            for(int j=0;j<layers.get(l).nodes.size();j++)
            {
                Node node = layers.get(l).nodes.get(j);
                for(int i=0;i<9;i++)
                {
                    String newId="";
                    for(int k=0;k<9;k++)
                    {
                        char ch = node.id.charAt(k);
                        if(k==i)ch='0';
                        newId+=ch;
                    }
                    if(!newId.equals(node.id))
                    {
                        try{
                            
                            layers.get(l-1).searchById(newId).sub_nodes.add(node);
                            //System.out.println(node.id+" : "+newId+" : "+l);
                        }
                        catch(NullPointerException e){}
                    }
                }
                
            }
        }
        begin.extractNodes();
    }
    
    @Override
    public void playTurn(int p,int turn){
        t = turn;
        if(turn != 1)
        {
            current = layers.get(turn-2).searchByState(TicTacToe.state);
        }
        if(turn == 1)
        {
            current = begin;
        }
        if(p==1)current.playNextn();
        if(p==2)current.playNext2();
    }
    
    @Override
    void notifyWin(int pl){
        if(pl==1)current.pref+=10;
        if(pl==2)current.pref-=10;
        current.nodeType=1;
        //System.out.println("*******"+current.id+":"+current.pref+" : "+layers.get(current.lNum-1).searchById(current.id).pref);
        saveMind();
    }
    @Override
    void notifyLose(int pl){
        if(pl==1)current.pref-=10;
        if(pl==2)current.pref+=10;
        current.nodeType=1;
        //System.out.println("*******"+current.id+":"+current.pref+" : "+layers.get(current.lNum-1).searchById(current.id).pref);
        saveMind();
    }
}
