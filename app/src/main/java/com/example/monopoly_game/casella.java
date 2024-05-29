package com.example.monopoly_game;

import java.util.ArrayList;

public class casella {
    String nome;
    ArrayList<Player> giocatori;
    String colore;
    int prezzo;
    Player proprietario;

    public casella(String nome, String colore,int prezzo) {
        this.nome = nome;
        this.colore = colore;
        giocatori = new ArrayList<>();
        this.prezzo=prezzo;
        proprietario=null;
    }
    public void addPlayer(Player player) {
        this.giocatori.add(player);
    }
    public void setProprietario(Player player){
        this.proprietario=player;
    }
    public void removePlayer(Player player) {
        this.giocatori.remove(player);
    }







}
