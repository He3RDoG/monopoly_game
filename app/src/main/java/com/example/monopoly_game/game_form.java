package com.example.monopoly_game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class game_form extends AppCompatActivity {


    casella[] caselle = new casella[40];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schermata_di_gioco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Trova il pulsante del giocatore e imposta un listener di click
        Button playerButton = findViewById(R.id.buttonplayer_1);
        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(game_form.this);

                // Imposta il titolo e il messaggio
                builder.setTitle("Dati del giocatore");
                builder.setMessage("Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5");

                // Imposta un pulsante positivo
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Chiudi la finestra di dialogo quando l'utente fa clic su OK
                        dialog.dismiss();
                    }
                });

                // Crea e mostra la finestra di dialogo
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        caselle[0] = new casella("Go", "Special",100);
        caselle[1] = new casella("Mediterranean Avenue", "Property",60);
        caselle[2] = new casella("Community Chest", "Community Chest",0);
        caselle[3] = new casella("Baltic Avenue", "Property",60);
        caselle[4] = new casella("Income Tax", "Tax",200);
        caselle[5] = new casella("Reading Railroad", "Railroad",200);
        caselle[6] = new casella("Oriental Avenue", "Property",100);
        caselle[7] = new casella("Chance", "Chance",0);
        caselle[8] = new casella("Vermont Avenue", "Property",100);
        caselle[9] = new casella("Connecticut Avenue", "Property",120);
        caselle[10] = new casella("Just Visiting", "Special",0);
        caselle[11] = new casella("St. Charles Place", "Property",140);
        caselle[12] = new casella("Electric Company", "Utility",150);
        caselle[13] = new casella("States Avenue", "Property",140);
        caselle[14] = new casella("Virginia Avenue", "Property",160);
        caselle[15] = new casella("Pennsylvania Railroad", "Railroad",200);
        caselle[16] = new casella("St. James Place", "Property",180);
        caselle[17] = new casella("Community Chest", "Community Chest",0);
        caselle[18] = new casella("Tennessee Avenue", "Property",180);
        caselle[19] = new casella("New York Avenue", "Property",200);
        caselle[20] = new casella("Free Parking", "Special",0);
        caselle[21] = new casella("Kentucky Avenue", "Property",220);
        caselle[22] = new casella("Chance", "Chance",0);
        caselle[23] = new casella("Indiana Avenue", "Property",220);
        caselle[24] = new casella("Illinois Avenue", "Property",240);
        caselle[25] = new casella("B. & O. Railroad", "Railroad",200);
        caselle[26] = new casella("Atlantic Avenue", "Property",260);
        caselle[27] = new casella("Ventnor Avenue", "Property",260);
        caselle[28] = new casella("Water Works", "Utility",150);
        caselle[29] = new casella("Marvin Gardens", "Property",280);
        caselle[30] = new casella("Go To Jail", "Special",0);
        caselle[31] = new casella("Pacific Avenue", "Property",300);
        caselle[32] = new casella("North Carolina Avenue", "Property",300);
        caselle[33] = new casella("Community Chest", "Community Chest",0);
        caselle[34] = new casella("Pennsylvania Avenue", "Property",320);
        caselle[35] = new casella("Short Line", "Railroad",200);
        caselle[36] = new casella("Chance", "Chance",0);
        caselle[37] = new casella("Park Place", "Property",350);
        caselle[38] = new casella("Luxury Tax", "Tax",100);
        caselle[39] = new casella("Boardwalk", "Property",400);

        // Crea un nuovo giocatore
        Player player = new Player(1, "Test Player");

        Random rand = new Random();

// Roll two dice
        int steps = rand.nextInt(6) + 1 + rand.nextInt(6) + 1;
        System.out.println("Player rolled: " + steps);
// Move the player
        caselle[player.move(steps, caselle)].addPlayer(player);
        System.out.println("Player is now on: " + caselle[player.posizione].nome);
        System.out.println("Player is on a " + caselle[player.posizione].giocatori.size());



    }
}