package com.yuri.listaattivita;

import android.provider.BaseColumns;

public class ContrattoAttivita {
    private ContrattoAttivita() {}

    public static final class EntryAttivita implements BaseColumns {
        public static final String NOME_TABELLA = "attivita";
        public static final String COLONNA_ID = "_id";
        public static final String COLONNA_TITOLO = "titolo";
        public static final String COLONNA_DESCRIZIONE = "descrizione";
        public static final String COLONNA_DATA_SCADENZA = "data_scadenza";
        public static final String COLONNA_PRIORITA = "priorita";
        public static final String COLONNA_COMPLETATA = "completata";
    }
}
