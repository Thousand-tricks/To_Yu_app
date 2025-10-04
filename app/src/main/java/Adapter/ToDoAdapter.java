package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.to_yu_app.MainActivity;
import com.example.to_yu_app.R;

import java.util.List;

import Model.ToDoModel;


//Scritto un adapter recycleView che crea le celle in una lista e collega i dati ad un altro modello
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    //2 variabili una per rappresentare i dati ed un altra per rifermineto activity main
    private List<ToDoModel> todoList;
    public MainActivity activity;

    public ToDoAdapter(MainActivity activity, List<ToDoModel> todoList) {
        this.todoList = todoList;
        this.activity = activity;
    }

    //qui il codice viene chiamato quando vogliamo creare una nuova cella
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()) //layout inflater carica i dati su una lista
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);//qui ritorna al ViewHolder per vedere elementi cella
    }


    //qui sotto colleghiamo i dati di una cella esistente prende un elemento todolist e aggiorna la checkbox
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoModel item = todoList.get(position);
        holder.taskCheckBox.setText(item.getTask());
        holder.taskCheckBox.setChecked(toBoolean(item.getStatus()));
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    //dice al recycleView quanti elementi ci sono nella lista

    public int getItemCount() {
        return todoList.size();
    }

    //ViewHolder memorizza i riferimenti grafici in una cella, nello specifico checkbox
    //In questo modo il RecyclerView non deve ogni volta cercare le View Con fieldbyid,ma utilizza i ViewHolder già creati
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox taskCheckBox;

        ViewHolder(View view) {
            super(view);
            taskCheckBox = view.findViewById(R.id.todoCheckBox);

        }
    }
}


