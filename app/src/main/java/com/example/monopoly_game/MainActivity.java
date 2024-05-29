package com.example.monopoly_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Giocolocale giocolocale;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button myButton = findViewById(R.id.buttonPlay);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Azioni da eseguire quando l'utente preme il pulsante
                // Ad esempio, mostra un messaggio di avviso (toast)
                Toast.makeText(MainActivity.this, "Avvio gioco", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, game_form.class);
                startActivity(intent);

            }
        });
        Button myButton2 = findViewById(R.id.buttonLogin);
        myButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Azioni da eseguire quando l'utente preme il pulsante
                // Ad esempio, mostra un messaggio di avviso (toast)
                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, login_form.class);
                startActivity(intent);

            }
        });

    }

}