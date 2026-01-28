package com.yuri.listaattivita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class GestoreDatabase extends SQLiteOpenHelper {

    private static final String NOME_DATABASE = "listaattivita.db";
    private static final int VERSIONE_DATABASE = 1;

    private static final String SQL_CREA_TABELLA =
            "CREATE TABLE " + ContrattoAttivita.EntryAttivita.NOME_TABELLA + " (" +
                    ContrattoAttivita.EntryAttivita.COLONNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ContrattoAttivita.EntryAttivita.COLONNA_TITOLO + " TEXT NOT NULL, " +
                    ContrattoAttivita.EntryAttivita.COLONNA_DESCRIZIONE + " TEXT, " +
                    ContrattoAttivita.EntryAttivita.COLONNA_DATA_SCADENZA + " TEXT, " +
                    ContrattoAttivita.EntryAttivita.COLONNA_PRIORITA + " TEXT, " +
                    ContrattoAttivita.EntryAttivita.COLONNA_COMPLETATA + " INTEGER DEFAULT 0);";

    public GestoreDatabase(Context context) {
        super(context, NOME_DATABASE, null, VERSIONE_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREA_TABELLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContrattoAttivita.EntryAttivita.NOME_TABELLA);
        onCreate(db);
    }

    public long aggiungiAttivita(String titolo, String descrizione, String dataScadenza, String priorita) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_TITOLO, titolo);
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_DESCRIZIONE, descrizione);
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_DATA_SCADENZA, dataScadenza);
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_PRIORITA, priorita);
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_COMPLETATA, 0);
        return db.insert(ContrattoAttivita.EntryAttivita.NOME_TABELLA, null, values);
    }

    public List<Attivita> ottieniTutteAttivita() {
        List<Attivita> attivita = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ContrattoAttivita.EntryAttivita.NOME_TABELLA,
                null, null, null, null, null, null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_ID));
                String titolo = cursor.getString(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_TITOLO));
                String descrizione = cursor.getString(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_DESCRIZIONE));
                String dataScadenza = cursor.getString(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_DATA_SCADENZA));
                String priorita = cursor.getString(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_PRIORITA));
                int completata = cursor.getInt(cursor.getColumnIndexOrThrow(ContrattoAttivita.EntryAttivita.COLONNA_COMPLETATA));

                attivita.add(new Attivita(id, titolo, descrizione, dataScadenza, priorita, completata == 1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return attivita;
    }

    public void eliminaAttivita(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContrattoAttivita.EntryAttivita.NOME_TABELLA,
                ContrattoAttivita.EntryAttivita.COLONNA_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void aggiornaStatoAttivita(int id, boolean completata) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContrattoAttivita.EntryAttivita.COLONNA_COMPLETATA, completata ? 1 : 0);
        db.update(ContrattoAttivita.EntryAttivita.NOME_TABELLA, values,
                ContrattoAttivita.EntryAttivita.COLONNA_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
