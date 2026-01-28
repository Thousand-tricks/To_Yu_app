package com.yuri.listaattivita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdapterAttivita extends RecyclerView.Adapter<AdapterAttivita.ViewHolderAttivita> {

    private List<Attivita> listaAttivita;
    private Context context;
    private InterfacciaListener listener;

    public interface InterfacciaListener {
        void onAttiviitaEliminata(int id);
        void onAttiviitaCompletata(int id, boolean completata);
    }

    public AdapterAttivita(List<Attivita> listaAttivita, Context context, InterfacciaListener listener) {
        this.listaAttivita = listaAttivita;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderAttivita onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attivita, parent, false);
        return new ViewHolderAttivita(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAttivita holder, int position) {
        Attivita attivita = listaAttivita.get(position);

        holder.txtTitolo.setText(attivita.getTitolo());
        holder.txtDescrizione.setText(attivita.getDescrizione());
        holder.txtData.setText("Scadenza: " + attivita.getDataScadenza());
        holder.txtPriorita.setText("Priorità: " + attivita.getPriorita());
        holder.checkCompletata.setChecked(attivita.isCompletata());

        holder.checkCompletata.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onAttiviitaCompletata(attivita.getId(), isChecked);
            }
        });

        holder.btnElimina.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAttiviitaEliminata(attivita.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAttivita.size();
    }

    public void aggiornaLista(List<Attivita> nuovaLista) {
        this.listaAttivita = nuovaLista;
        notifyDataSetChanged();
    }

    public static class ViewHolderAttivita extends RecyclerView.ViewHolder {
        public TextView txtTitolo;
        public TextView txtDescrizione;
        public TextView txtData;
        public TextView txtPriorita;
        public CheckBox checkCompletata;
        public ImageButton btnElimina;

        public ViewHolderAttivita(@NonNull View itemView) {
            super(itemView);
            txtTitolo = itemView.findViewById(R.id.txt_titolo);
            txtDescrizione = itemView.findViewById(R.id.txt_descrizione);
            txtData = itemView.findViewById(R.id.txt_data);
            txtPriorita = itemView.findViewById(R.id.txt_priorita);
            checkCompletata = itemView.findViewById(R.id.check_completata);
            btnElimina = itemView.findViewById(R.id.btn_elimina);
        }
    }
}
