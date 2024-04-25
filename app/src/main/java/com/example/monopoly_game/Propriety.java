package com.example.monopoly_game;

public class Propriety {
    int indiceAzionarioProprietà;
    int ncase;
    int costoCasa;
    boolean albergo;
    public boolean togliCasa()
    {
        if(albergo) {
            ncase=4;
            albergo=false;
        }
        else if(ncase>0)
            ncase--;
        else return false;
        return true;
    }
    public boolean mettiCasa()
    {
        if(ncase<4&&!albergo)
            ncase++;
        else if(albergo)
        {
            ncase=0; albergo=true;
        }
        else return false;
        return true;
    }
}
