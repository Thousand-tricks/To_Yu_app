package com.yuri.listaattivita;

public class Attivita {
    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private String priorita;
    private boolean completata;

    public Attivita(int id, String titolo, String descrizione, String dataScadenza, String priorita, boolean completata) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        this.priorita = priorita;
        this.completata = completata;
    }

    // Getters
    public int getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getDescrizione() { return descrizione; }
    public String getDataScadenza() { return dataScadenza; }
    public String getPriorita() { return priorita; }
    public boolean isCompletata() { return completata; }

    // Setters
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setDataScadenza(String dataScadenza) { this.dataScadenza = dataScadenza; }
    public void setPriorita(String priorita) { this.priorita = priorita; }
    public void setCompletata(boolean completata) { this.completata = completata; }
}
