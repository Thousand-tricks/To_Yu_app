package com.yuri.listaattivita;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AggiungiAttivitaActivity extends AppCompatActivity {

    private EditText edtTitolo;
    private EditText edtDescrizione;
    private TextView txtDataScadenza;
    private Spinner spinnerPriorita;
    private Button btnSelezionaData;
    private Button btnAggiungi;
    private GestoreDatabase db;
    private Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_attivita);

        edtTitolo = findViewById(R.id.edt_titolo);
        edtDescrizione = findViewById(R.id.edt_descrizione);
        txtDataScadenza = findViewById(R.id.txt_data_scadenza);
        spinnerPriorita = findViewById(R.id.spinner_priorita);
        btnSelezionaData = findViewById(R.id.btn_seleziona_data);
        btnAggiungi = findViewById(R.id.btn_aggiungi);

        db = new GestoreDatabase(this);
        calendario = Calendar.getInstance();

        // Configurare lo spinner della priorità
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorita_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriorita.setAdapter(adapter);

        btnSelezionaData.setOnClickListener(v -> mostraDatePicker());

        btnAggiungi.setOnClickListener(v -> aggiungiAttivita());
    }

    private void mostraDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String data = dayOfMonth + "/" + (month + 1) + "/" + year;
                    txtDataScadenza.setText(data);
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void aggiungiAttivita() {
        String titolo = edtTitolo.getText().toString().trim();
        String descrizione = edtDescrizione.getText().toString().trim();
        String data = txtDataScadenza.getText().toString().trim();
        String priorita = spinnerPriorita.getSelectedItem().toString();

        if (titolo.isEmpty()) {
            Toast.makeText(this, "Inserisci il titolo dell'attività", Toast.LENGTH_SHORT).show();
            return;
        }

        if (data.isEmpty()) {
            Toast.makeText(this, "Seleziona una data di scadenza", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = db.aggiungiAttivita(titolo, descrizione, data, priorita);

        if (result != -1) {
            Toast.makeText(this, "Attività aggiunta con successo!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Errore durante l'aggiunta dell'attività", Toast.LENGTH_SHORT).show();
        }
    }
}
