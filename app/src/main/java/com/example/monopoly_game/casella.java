package com.example.monopoly_game;

import java.util.ArrayList;

public class casella {
    String nome;
    ArrayList<Player> giocatori;
    String colore;
    int prezzo;
    Player proprietario;
    boolean hotel;
    int caseNum;

    public casella(String nome, String colore,int prezzo) {
        this.nome = nome;
        this.colore = colore;
        giocatori = new ArrayList<>();
        this.prezzo=prezzo;
        proprietario=null;
        hotel=false;
        caseNum=0;
    }
    public void addPlayer(Player player) {
        this.giocatori.add(player);

    }
    public int getRent(){
        return this.prezzo/8;
    }
    public String getNome(){
        return this.nome;
    }
    public void setProprietario(Player player){
        this.proprietario=player;
    }
    public void removePlayer(Player player) {
        this.giocatori.remove(player);
    }







}
