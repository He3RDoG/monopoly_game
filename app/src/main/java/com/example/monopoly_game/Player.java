package com.example.monopoly_game;

import java.util.Comparator;


import java.util.ArrayList;

public class Player extends unityBase {
    int id;
    String username;
    ArrayList<Banconote> soldi;
    public int posizione;
    boolean prigione;
    ArrayList<casella> proprietà;
    Boolean bot;

public Player(int id, String username) {
        this.id = id;
        this.username = username;
        this.soldi = new ArrayList<>();
        initSoldi();
        this.posizione = 0;
        this.prigione = false;
        this.proprietà = new ArrayList<>();
        bot = true;
    }
    public void setPlayer()
    {
        bot=false;
    }
    public void initSoldi() {
        this.soldi.add(new Banconote(500, "viola"));
        this.soldi.add(new Banconote(500, "viola"));
        this.soldi.add(new Banconote(100, "verde"));
        this.soldi.add(new Banconote(100, "verde"));
        this.soldi.add(new Banconote(100, "verde"));
        this.soldi.add(new Banconote(100, "verde"));
        this.soldi.add(new Banconote(50, "blu"));
        this.soldi.add(new Banconote(20, "giallo"));
        this.soldi.add(new Banconote(10, "rosso"));
        this.soldi.add(new Banconote(10, "rosso"));
        this.soldi.add(new Banconote(5, "rosa"));
        this.soldi.add(new Banconote(1, "bianco"));
        this.soldi.add(new Banconote(1, "bianco"));
        this.soldi.add(new Banconote(1, "bianco"));
        this.soldi.add(new Banconote(1, "bianco"));
        this.soldi.add(new Banconote(1, "bianco"));
    }
    public void addProprietà(casella casella) {
        this.proprietà.add(casella);
    }
    public ArrayList<Banconote> giveChange(int change) {
        int[] billValues = {500, 100, 50, 20, 10, 5, 1};
        ArrayList<Banconote> changeBills = new ArrayList<>();

        for (int value : billValues) {
            while (change >= value) {
                changeBills.add(new Banconote(value, "bianco"));
                change -= value;
            }
        }

        return changeBills;
    }
    public int getMoney() {
        return this.soldi.stream().mapToInt(Banconote::getValore).sum();
    }

    public void pay(int amount) {
        // Sort the player's money in ascending order
        this.soldi.sort(Comparator.comparing(Banconote::getValore));

        // Check if the player has enough money
        int total = this.soldi.stream().mapToInt(Banconote::getValore).sum();
        if (total < amount) {
            System.out.println("You don't have enough money to make this payment.");
            return;
        }

        // Make the payment
        for (int i = 0; i < this.soldi.size(); i++) {
            Banconote bill = this.soldi.get(i);
            if (bill.getValore() <= amount) {
                amount -= bill.getValore();
                this.soldi.remove(i);
                i--;
            } else if (bill.getValore() > amount) {
                for (int j = i + 1; j < this.soldi.size(); j++) {
                    Banconote smallerBill = this.soldi.get(j);
                    if (smallerBill.getValore() <= amount) {
                        amount -= smallerBill.getValore();
                        this.soldi.remove(j);
                        break;
                    }
                }
                if (amount == 0) {
                    break;
                }
            }
        }

        // Calculate and return the change
        if (amount > 0) {
            int change = this.soldi.get(0).getValore() - amount;
            this.soldi.remove(0);
            ArrayList<Banconote> changeBills = giveChange(change);
            this.soldi.addAll(changeBills);
            System.out.println("Your change is: " + change);
        }
    }
    public int move(int steps, casella[] caselle) {
        int oldPosition = this.posizione;
        this.posizione = (this.posizione + steps) % caselle.length; // Move the player and wrap around if they pass the last square

        // If the player passed the last square, add 200 to their money
        if (this.posizione < oldPosition) {
            this.soldi.add(new Banconote(100, "verde"));
            this.soldi.add(new Banconote(100, "verde"));
            System.out.println("You passed Go. Collect 200.");
        }

        System.out.println("Moved to: " + caselle[this.posizione].nome);
        return this.posizione;
    }




}
