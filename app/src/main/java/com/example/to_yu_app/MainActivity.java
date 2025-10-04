package com.example.to_yu_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.ToDoAdapter;
import Model.ToDoModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //collega ui all'actibity

        //getSupportActionBar().hide(); // nasconde la barra di navigazione

        taskList = new ArrayList<>(); //crea una nuova lista



        taskRecyclerView =findViewById(R.id.taskRecyclerView); // recupera il RecyclerView dal layout

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));// imposta il layout verticale a lista

        tasksAdapter = new ToDoAdapter(this, taskList); //crea un nuovo adapter

        taskRecyclerView.setAdapter(tasksAdapter); //imposta l'adapter nel RecyclerView

        ToDoModel task = new ToDoModel(); //crea un nuovo task

        task.setTask("this test is task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task); //aggiunge il task alla lista
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksAdapter.setTasks(taskList); //imposta la lista di task nel adapter





    }
}