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

public class game_form extends AppCompatActivity {

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
    }
}