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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    Button myButton = findViewById(R.id.contratta_p1);
myButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(game_form.this, "Player_1 vuole contrattare", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(game_form.this, activity_contrattazioni.class);
        startActivity(intent);
    }});
    Button casella =findViewById(R.id.casella_1);
    casella.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(game_form.this, "Player_1 vuole contrattare", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(game_form.this, Dialog_casella.class);
            startActivity(intent);
        }});
Button azioni = findViewById(R.id.azioni_1);
azioni.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(game_form.this, "Player_1 vuole comprare azioni", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(game_form.this, Dialog_aqcuisto_azioni.class);
        startActivity(intent);
    }


});
    }

       /* Button myButton = findViewById(R.id.contratta_1);
        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/
       /* BlankFragment myFragment = new BlankFragment(); // Sostituisci con il tuo Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null) // Aggiungi alla back stack per la navigazione
                        .commit();

      /*  fragmentTransaction.add(R.id.fragment_container, myFragment);
        fragmentTransaction.commitNow();
            }
        });*/
}