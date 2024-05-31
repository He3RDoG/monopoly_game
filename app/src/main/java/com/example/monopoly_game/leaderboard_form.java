package com.example.monopoly_game;
import android.os.Handler;

import android.os.Bundle;
import java.util.Collections;
import java.util.Comparator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class leaderboard_form extends AppCompatActivity {
    private Handler handler = new Handler();
    private Runnable runnableCode;
    ExecutorService executor = Executors.newSingleThreadExecutor();


    // Modello per i dati della classifica
    public static class Player {
        @SerializedName("posizione_in_gioco")
        private int name;
        @SerializedName("id_partita")
        public int score;

        public Player(int name, int score) {
            this.name = name;
            this.score = score;
        }
        public String getName() {
            return String.valueOf(name);
        }
        public int getScore() {
            return score;
        }


    }


    public static class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
        private List<Player> players;

        public PlayerAdapter(List<Player> players) {
            this.players = players;
        }

        @Override
        public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_player, parent, false); // changed from leaderboard to list_item_player
            return new PlayerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PlayerViewHolder holder, int position) {
            Player player = players.get(position);
            holder.name.setText(String.valueOf(player.getName()));
            holder.score.setText(String.valueOf(player.getScore()));
        }

        @Override
        public int getItemCount() {
            return players.size();
        }

        public static class PlayerViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView score;

            public PlayerViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.player_name);
                score = itemView.findViewById(R.id.player_score);

            }
        }
    }
    String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.leaderboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.leaderboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final ArrayList<Player>[] players = new ArrayList[]{new ArrayList<>()};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
                finish();
            }
        });

        runnableCode = new Runnable() {
            @Override
            public void run() {

                try {
                    Future<String> future = leggiGiocatori();
                     result = future.get();
                    Gson gson = new Gson();
                    Type playerListType = new TypeToken<ArrayList<Player>>(){}.getType();
                    ArrayList<Player> newPlayers = gson.fromJson(result, playerListType);

                    Collections.sort(newPlayers, new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                            return Integer.compare(p2.getScore(), p1.getScore()); // Ordine decrescente
                        }
                    });
                    PlayerAdapter adapter = new PlayerAdapter(newPlayers);


                    recyclerView.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }



                handler.postDelayed(runnableCode, 500);
            }
        };
          handler.post(runnableCode);




        PlayerAdapter adapter = new PlayerAdapter(players[0]);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnableCode);
    }


    private void updateLeaderboard() {
        Gson gson = new Gson();
        Type playerListType = new TypeToken<ArrayList<Player>>(){}.getType();
        ArrayList<Player> newPlayers = gson.fromJson(result, playerListType);

        // Aggiorna l'adapter con la nuova lista di giocatori

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

}