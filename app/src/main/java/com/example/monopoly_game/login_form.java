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

public class login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button myButton = findViewById(R.id.backButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Azioni da eseguire quando l'utente preme il pulsante
                // Ad esempio, mostra un messaggio di avviso (toast)
                Toast.makeText(login_form.this, "Avvio gioco", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login_form.this, MainActivity.class);
                finish();
                startActivity(intent);

            }
        });


    }


}