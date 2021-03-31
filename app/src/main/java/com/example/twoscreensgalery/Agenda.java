package com.example.twoscreensgalery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Agenda extends AppCompatActivity {

    private Button bttAdd;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        setTitle("Agenda");
        setupUIViews();
    }

    private void setupUIViews() {
        bttAdd = (Button) findViewById(R.id.button_Add);
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupListView();
    }

    public void setupListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1, UserManager.getInstance().GetInformationNameList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Agenda.this, EditArea.class);
                EditArea.actualInfo = UserManager.getInstance().GetInformationById(position);
                startActivity(intent);
            }
        });
    }

    public void OnButtonAddClicked(View view){
        Intent intent = new Intent(this, EditArea.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atençao!");
        builder.setMessage("Voce realmente deseja sair de sua conta?");
        builder.setPositiveButton(android.R.string.no, null);
        builder.setNegativeButton(android.R.string.yes,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        UserManager.getInstance().LogOut();
                        finish();
                    }
                });

        builder.create().show();
    }
}