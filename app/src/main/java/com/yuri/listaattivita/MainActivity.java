package com.yuri.listaattivita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterAttivita.InterfacciaListener {

    private RecyclerView recyclerView;
    private AdapterAttivita adapter;
    private GestoreDatabase db;
    private List<Attivita> listaAttivita;
    private Button btnAggiungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new GestoreDatabase(this);
        recyclerView = findViewById(R.id.recycler_view);
        btnAggiungi = findViewById(R.id.btn_aggiungi);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        caricaAttivita();

        btnAggiungi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AggiungiAttivitaActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        caricaAttivita();
    }

    private void caricaAttivita() {
        listaAttivita = db.ottieniTutteAttivita();
        adapter = new AdapterAttivita(listaAttivita, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttiviitaEliminata(int id) {
        db.eliminaAttivita(id);
        caricaAttivita();
        Toast.makeText(this, "Attività eliminata", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttiviitaCompletata(int id, boolean completata) {
        db.aggiornaStatoAttivita(id, completata);
        Toast.makeText(this, completata ? "Attività completata" : "Attività non completata", Toast.LENGTH_SHORT).show();
    }
}
