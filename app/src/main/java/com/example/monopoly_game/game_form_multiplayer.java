package com.example.monopoly_game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Future;

public class game_form_multiplayer extends AppCompatActivity {


    ExecutorService executor = Executors.newSingleThreadExecutor();

    casella[] caselle = new casella[38];
    boolean isRunning = true;
ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView24;
    ImageView imageView25;
    ImageView imageView26;
    ImageView imageView27;
    ImageView imageView28;
    ImageView imageView29;
    ImageView imageView30;
    ImageView imageView31;
    ImageView imageView33;
    ImageView imageView34;
    ImageView imageView35;
    ImageView imageView36;
    ImageView imageView37;
    ImageView imageView38;
    ImageView imageView39;
    ImageView imageView40;
    ImageView imageView41;
    ImageView imageView42;
    ImageView imageView43;
    ImageView imageView44;
    ImageView imageView45;
    ImageView imageView46;
    ImageView imageView47;
    ImageView imageView48;
    ImageView imageView49;
    ImageView imageView50;
    ImageView imageView51;
    ImageView imageView52;
    ImageView imageView53;
    ImageView imageView54;
    TextView contoplayer_1;
    TextView contoplayer_2;
    TextView contoplayer_3;
    TextView contoplayer_4;
    int turno=1;
    Player currentPlayer;
    List<Player> players;

    Player player = new Player(1, "Test Player");

    Player player1 = new Player(2, "Test Player");
    Player player2 = new Player(3, "Test Player");
    Player player3 = new Player(4, "Test Player");

    Button giocaButton;

    int numeroBot;
    Handler handler = new Handler();
    int botTurnCount = 0; // Aggiungi un contatore per i turni dei bot
    ArrayList<Player> giocatori = new ArrayList<>();


    Runnable botTurnRunnable = new Runnable() {
        @Override
        public void run() {
            // Mostra nuovamente il pulsante "gira" alla fine del turno del bot
            giocaButton.setVisibility(View.VISIBLE);

            // Incrementa il turno
            turno++;
            if (turno > 4) {
                turno = 1;
            }

            // Ottieni il prossimo giocatore
            Player nextPlayer;
            switch (turno) {
                case 1:
                    nextPlayer = player;
                    break;
                case 2:
                    nextPlayer = player1;
                    break;
                case 3:
                    nextPlayer = player2;
                    break;
                case 4:
                    nextPlayer = player3;
                    break;
                default:
                    nextPlayer = player;
                    break;
            }

            // Se il prossimo giocatore è un bot, avvia il suo turno automaticamente
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schermata_di_gioco_multiplayer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        giocaButton = findViewById(R.id.buttongioca);
        giocaButton.setVisibility(View.INVISIBLE);

        Future<String> future = leggiGiocatori();
        try {
            String response = future.get();

            // Utilizza la risposta qui
            Gson gson = new Gson();
            Type playerListType = new TypeToken<List<Player>>(){}.getType();
            players = gson.fromJson(response, playerListType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        addPlayer(player);
        if (player.getId()==1)
        {
            while(players.size()>1)
            {
                giocaButton.setVisibility(View.VISIBLE);
                gioca();
                break;

            }
        }
        else {
            handler.post(runnableCode);
        }








        // Trova il pulsante del giocatore e imposta un listener di click
        Button playerButton = findViewById(R.id.buttonplayer_1);
        Button playerButton1 = findViewById(R.id.buttonplayer_2);
        Button playerButton2 = findViewById(R.id.buttonplayer_3);
        Button playerButton3 = findViewById(R.id.buttonplayer_4);

        playerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(game_form_multiplayer.this);

                // Imposta il titolo e il messaggio
                builder.setTitle("Dati del giocatore");
                builder.setMessage("Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5");
                String message = "Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5";
                String properties = player1.proprietà.stream().map(casella::getNome).reduce("", (a, b) -> a + ", " + b);
                if (!properties.isEmpty()) {
                    message += "\nProprietà: " + properties;
                }
                builder.setMessage(message);

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
        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(game_form_multiplayer.this);

                // Imposta il titolo e il messaggio
                builder.setTitle("Dati del giocatore");
                builder.setMessage("Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5");
                String message = "Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5";
                String properties = player.proprietà.stream().map(casella::getNome).reduce("", (a, b) -> a + ", " + b);
                if (!properties.isEmpty()) {
                    message += "\nProprietà: " + properties;
                }
                builder.setMessage(message);

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


        playerButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(game_form_multiplayer.this);

                // Imposta il titolo e il messaggio
                builder.setTitle("Dati del giocatore");
                builder.setMessage("Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5");
                String message = "Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5";
                String properties = player2.proprietà.stream().map(casella::getNome).reduce("", (a, b) -> a + ", " + b);
                if (!properties.isEmpty()) {
                    message += "\nProprietà: " + properties;
                }
                builder.setMessage(message);

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
        playerButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(game_form_multiplayer.this);

                // Imposta il titolo e il messaggio
                builder.setTitle("Dati del giocatore");
                builder.setMessage("Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5");
                String message = "Nome: Mario\nCognome: Rossi\nUsername: mario_rossi\nPartite giocate: 10\nVittorie: 5";
                String properties = player3.proprietà.stream().map(casella::getNome).reduce("", (a, b) -> a + ", " + b);
                if (!properties.isEmpty()) {
                    message += "\nProprietà: " + properties;
                }
                builder.setMessage(message);

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
         contoplayer_1 = findViewById(R.id.contoplayer_1);
        contoplayer_1.setText(player.getMoney() + "€");
        contoplayer_2 = findViewById(R.id.contoplayer_2);
        contoplayer_2.setText(player.getMoney() + "€");
        contoplayer_3 = findViewById(R.id.label_contoplayer_3);
        contoplayer_3.setText(player.getMoney() + "€");
        contoplayer_4 = findViewById(R.id.label_contoplayer_4);
        contoplayer_4.setText(player.getMoney() + "€");



        giocaButton.setOnClickListener(new View.OnClickListener() {

                                           @Override
                                           public void onClick(View v) {
                                               botTurnCount=0;

                                               switch (turno) {
                                                   case 1:
                                                       currentPlayer = player;
                                                       break;
                                                   case 2:
                                                       currentPlayer = player1;
                                                       break;
                                                   case 3:
                                                       currentPlayer = player2;
                                                       break;
                                                   case 4:
                                                       currentPlayer = player3;
                                                       break;
                                                   default:
                                                       currentPlayer = player;
                                                       break;
                                               }


                                                   Random rand = new Random();

                                                   // Roll two dice
                                                   int steps = rand.nextInt(6) + 1 + rand.nextInt(6) + 1;
                                                   System.out.println("Player rolled: " + steps);

                                                   caselle[currentPlayer.posizione].removePlayer(currentPlayer);
                                                   caselle[currentPlayer.move(steps, caselle)].addPlayer(currentPlayer);
                                                   System.out.println("Player is now on: " + caselle[currentPlayer.posizione].nome);
                                                   System.out.println("Player is on a " + caselle[currentPlayer.posizione].giocatori.size());
                                                   pedine();

                                                   // Mostra a schermo quanto è uscito dal dado
                                                   Toast.makeText(game_form_multiplayer.this, "Player rolled: " + steps, Toast.LENGTH_SHORT).show();

                                                   // Crea un AlertDialog.Builder
                                                   AlertDialog.Builder builder = new AlertDialog.Builder(game_form_multiplayer.this);


                                                   if (caselle[currentPlayer.posizione].proprietario == currentPlayer) {
                                                       // Ask the player if they want to buy a house
                                                       AlertDialog.Builder builder1 = new AlertDialog.Builder(game_form_multiplayer.this);
                                                       builder1.setTitle("Buy House");
                                                       builder1.setMessage("Do you want to buy a house for " + (caselle[currentPlayer.posizione].prezzo * 0.75) + "?");
                                                       builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               if (currentPlayer.getMoney() > caselle[currentPlayer.posizione].prezzo * 0.75) {
                                                                   currentPlayer.pay((int) (caselle[currentPlayer.posizione].prezzo * 0.75));
                                                                   caselle[currentPlayer.posizione].caseNum++;
                                                                   if (caselle[currentPlayer.posizione].caseNum == 4) {
                                                                       caselle[currentPlayer.posizione].hotel = true;
                                                                   }
                                                                   Toast.makeText(game_form_multiplayer.this, "You bought a house on: " + caselle[currentPlayer.posizione].nome, Toast.LENGTH_SHORT).show();
                                                               } else {
                                                                   Toast.makeText(game_form_multiplayer.this, "You don't have enough money to buy a house.", Toast.LENGTH_SHORT).show();
                                                               }
                                                           }
                                                       });
                                                       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               // User cancelled the dialog
                                                           }
                                                       });
                                                       AlertDialog dialog = builder.create();
                                                       dialog.show();
                                                   }
                                                   // Controlla se la casella ha un proprietario
                                                   if (caselle[currentPlayer.posizione].proprietario == null) {
                                                       // Imposta il titolo e il messaggio
                                                       builder.setTitle("Casella senza proprietario");
                                                       builder.setMessage("Costo: " + caselle[currentPlayer.posizione].prezzo);

                                                       // Imposta un pulsante positivo
                                                       builder.setPositiveButton("Compra", new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               // Il giocatore compra la casella
                                                               currentPlayer.pay(caselle[currentPlayer.posizione].prezzo);
                                                               caselle[currentPlayer.posizione].setProprietario(player);
                                                               currentPlayer.addProprietà(caselle[currentPlayer.posizione]);
                                                               aggiorna();
                                                               dialog.dismiss();

                                                           }

                                                       });

                                                       // Imposta un pulsante negativo
                                                       builder.setNegativeButton("Rifiuta", new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               // Il giocatore rifiuta di comprare la casella
                                                               dialog.dismiss();
                                                           }
                                                       });
                                                   } else {
                                                       // Imposta il titolo e il messaggio
                                                       builder.setTitle("Casella con proprietario");
                                                       builder.setMessage("Il giocatore ha pagato: " + caselle[currentPlayer.posizione].getRent());

                                                       // Imposta un pulsante positivo
                                                       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               // Il giocatore paga l'affitto
                                                               currentPlayer.pay(caselle[currentPlayer.posizione].getRent());
                                                               dialog.dismiss();
                                                           }

                                                       });

                                                   }

                                                   // Crea e mostra la finestra di dialogo
                                                   AlertDialog dialog = builder.create();
                                                   dialog.show();
                                                   nextTurn();
                                               }




        }
        );

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


        // Crea un nuovo giocatore



        Random rand = new Random();
        caselle[0].addPlayer(player);
        caselle[0].addPlayer(player1);
        caselle[0].addPlayer(player2);
        caselle[0].addPlayer(player3);

pedine();
         this.imageView7 = findViewById(R.id.imageView7);
        this.imageView8 = findViewById(R.id.imageView8);
        this.imageView9 = findViewById(R.id.imageView9);
        this.imageView10 = findViewById(R.id.imageView10);
        this.imageView11 = findViewById(R.id.imageView11);
        this.imageView12 = findViewById(R.id.imageView12);
        this.imageView13 = findViewById(R.id.imageView13);
        this.imageView14 = findViewById(R.id.imageView14);
        this.imageView15 = findViewById(R.id.imageView15);
        this.imageView16 = findViewById(R.id.imageView16);
        this.imageView17 = findViewById(R.id.imageView17);
        this.imageView19 = findViewById(R.id.imageView19);
        this.imageView20 = findViewById(R.id.imageView20);
        this.imageView21 = findViewById(R.id.imageView21);
        this.imageView22 = findViewById(R.id.imageView22);
        this.imageView23 = findViewById(R.id.imageView23);
        this.imageView24 = findViewById(R.id.imageView24);
        this.imageView25 = findViewById(R.id.imageView25);
        this.imageView26 = findViewById(R.id.imageView26);
        this.imageView27 = findViewById(R.id.imageView27);
        this.imageView28 = findViewById(R.id.imageView28);
        this.imageView29 = findViewById(R.id.imageView29);
        this.imageView30 = findViewById(R.id.imageView30);
        this.imageView31 = findViewById(R.id.imageView31);
        this.imageView33 = findViewById(R.id.imageView33);
        this.imageView34 = findViewById(R.id.imageView34);
        this.imageView35 = findViewById(R.id.imageView35);
        this.imageView36 = findViewById(R.id.imageView36);
        this.imageView37 = findViewById(R.id.imageView37);
        this.imageView38 = findViewById(R.id.imageView38);
        this.imageView39 = findViewById(R.id.imageView39);
        this.imageView40 = findViewById(R.id.imageView40);
        this.imageView41 = findViewById(R.id.imageView41);
        this.imageView42 = findViewById(R.id.imageView42);
        this.imageView43 = findViewById(R.id.imageView43);
        this.imageView44 = findViewById(R.id.imageView44);
        this.imageView45 = findViewById(R.id.imageView45);
        this.imageView46 = findViewById(R.id.imageView46);
        this.imageView47 = findViewById(R.id.imageView47);
        this.imageView48 = findViewById(R.id.imageView48);
        this.imageView49 = findViewById(R.id.imageView49);
        this.imageView50 = findViewById(R.id.imageView50);
        this.imageView51 = findViewById(R.id.imageView51);
        this.imageView52 = findViewById(R.id.imageView52);
        this.imageView53 = findViewById(R.id.imageView53);
        this.imageView54 = findViewById(R.id.imageView54);
        pedine();





    }
    public void aggiorna(){
        contoplayer_1.setText(player.getMoney() + "€");
        contoplayer_2.setText(player1.getMoney() + "€");
        contoplayer_3.setText(player2.getMoney() + "€");
        contoplayer_4.setText(player3.getMoney() + "€");
    }
    public void pedine() {


        ImageView[] immagini = {imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, imageView19, imageView20, imageView21, imageView22, imageView23, imageView24, imageView25, imageView26, imageView27, imageView28, imageView29, imageView30, imageView31, imageView33, imageView34, imageView35, imageView36, imageView37, imageView38, imageView39, imageView40, imageView41, imageView42, imageView43, imageView44, imageView45, imageView46, imageView47, imageView48, imageView49, imageView50, imageView51, imageView52, imageView53, imageView54};
       for (int i=0;immagini.length>i;i++) {
           if (immagini[i] != null) {
               immagini[i].setImageDrawable(null);
           }
       }
        for (int i = 0; i < caselle.length; i++) {
            if (i < immagini.length && immagini[i] != null) {
                // Ottieni il numero di giocatori in questa posizione
                int numberOfPlayers = caselle[i].giocatori.size();

                // Imposta l'immagine corrispondente al numero di giocatori
                if (numberOfPlayers == 1) {
                    if (caselle[i].giocatori.get(0).id == 1)
                        immagini[i].setImageResource(R.drawable.png2);
                    if (caselle[i].giocatori.get(0).id == 2)
                        immagini[i].setImageResource(R.drawable.png6);
                    if (caselle[i].giocatori.get(0).id == 3)
                        immagini[i].setImageResource(R.drawable.png7);
                    if (caselle[i].giocatori.get(0).id == 4)
                        immagini[i].setImageResource(R.drawable.png8);
                } else if (numberOfPlayers == 2) {
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 2)
                        immagini[i].setImageResource(R.drawable.png3);
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 3)
                        immagini[i].setImageResource(R.drawable.png9);
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 4)
                        immagini[i].setImageResource(R.drawable.png10);
                    if (caselle[i].giocatori.get(0).id == 2 && caselle[i].giocatori.get(1).id == 3)
                        immagini[i].setImageResource(R.drawable.png11);
                    if (caselle[i].giocatori.get(0).id == 2 && caselle[i].giocatori.get(1).id == 4)
                        immagini[i].setImageResource(R.drawable.png12);
                    if (caselle[i].giocatori.get(0).id == 3 && caselle[i].giocatori.get(1).id == 4)
                        immagini[i].setImageResource(R.drawable.png13);

                } else if (numberOfPlayers == 3) {
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 2 && caselle[i].giocatori.get(2).id == 3)
                        immagini[i].setImageResource(R.drawable.png14);
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 2 && caselle[i].giocatori.get(2).id == 4)
                        immagini[i].setImageResource(R.drawable.png15);
                    if (caselle[i].giocatori.get(0).id == 1 && caselle[i].giocatori.get(1).id == 3 && caselle[i].giocatori.get(2).id == 4)
                        immagini[i].setImageResource(R.drawable.png16);
                    if (caselle[i].giocatori.get(0).id == 2 && caselle[i].giocatori.get(1).id == 3 && caselle[i].giocatori.get(2).id == 4)
                        immagini[i].setImageResource(R.drawable.png17);
                } else if (numberOfPlayers == 4) {

                    immagini[i].setImageResource(R.drawable.png5);
                } else {
                    immagini[i].setImageDrawable(null);
                }
            }
        }
    }


    public void nextTurn() {
        // Increment the turn
        turno++;
        giocatori.add(player);
        //if (turno > giocatori.length) { // Change 4 to the actual number of players

         //   turno = 1;
        //}

        // Get the next player
        //Player nextPlayer = giocatori[turno - 1]; // Use array indexing to get the next player

        // If the next player is a bot, start their turn automatically

    }
    public Future<String> leggiGiocatori() {
        return executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String response = "";
                try {
                    URL url = new URL("https://5ailucastangherlin.barsanti.edu.it/api_monopoly/Giocatori_mobili");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response1 = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response1.append(inputLine);
                    }
                    response = response1.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
        });
    }


    public void addPlayer(Player newPlayer) {
        if (players.size() >= 4) {
            // Mostra un AlertDialog se ci sono già 4 giocatori
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Errore");
            builder.setMessage("Ci sono già 4 giocatori.");
            builder.setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            players.add(newPlayer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Crea un oggetto URL
                        URL url = new URL("https://5ailucastangherlin.barsanti.edu.it/api_monopoly/userInPartita");

                        // Crea una connessione HttpURLConnection
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        // Imposta il metodo di richiesta a POST
                        conn.setRequestMethod("POST");

                        // Imposta l'intestazione Content-Type
                        conn.setRequestProperty("Content-Type", "application/json; utf-8");

                        // Abilita l'output e l'input
                        conn.setDoOutput(true);
                        conn.setDoInput(true);

                        // Crea un oggetto JSON con i dati del giocatore
                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("id", player.getId());
                        jsonParam.put("posizione_in_gioco", player.posizione);
                        jsonParam.put("soldi_correnti", player.getMoney());
                        jsonParam.put("Proprio_turno", player.turno);
                        jsonParam.put("id_partita", 1);


                        // Scrive i dati JSON nella connessione
                        try(OutputStream os = conn.getOutputStream()) {
                            byte[] input = jsonParam.toString().getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }

                        // Ottieni la risposta
                        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                            StringBuilder response = new StringBuilder();
                            String responseLine = null;
                            while ((responseLine = br.readLine()) != null) {
                                response.append(responseLine.trim());
                            }
                            System.out.println(response.toString());
                        }

                        // Chiudi la connessione
                        conn.disconnect();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
    Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Esegui la chiamata all'API qui
            Future<String> future = leggiGiocatori();
            try {
                String response = future.get();
                // Utilizza la risposta qui
                Gson gson = new Gson();
                Type playerListType = new TypeToken<List<Player>>(){}.getType();
                List<Player> players = gson.fromJson(response, playerListType);

                // Controlla se è il turno del giocatore
                for (Player player : players) {
                    if (player.turno == turno) {
                        isRunning = false;
                        giocaButton.setVisibility(View.VISIBLE);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Ripeti questo runnable code ogni mezzo secondo solo se isRunning è true
            if (isRunning) {
                handler.postDelayed(this, 500);
            }
        }
    };
    public void gioca()
    {

    }

}



