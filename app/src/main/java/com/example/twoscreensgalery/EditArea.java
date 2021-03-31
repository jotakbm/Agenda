package com.example.twoscreensgalery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditArea extends AppCompatActivity {

    private Button bttSave;
    private Spinner spinner;
    private TextView nameText;
    private TextView addressText;
    private TextView phoneText;
    public static Information actualInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_area);
        setTitle("Edit Area");
        setupUIViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(actualInfo != null) {
            spinner.setSelection(actualInfo.spinnerIndex);
            nameText.setText(actualInfo.name);
            addressText.setText(actualInfo.address);
            phoneText.setText(actualInfo.phone);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        actualInfo = null;
    }

    private void setupUIViews() {
        bttSave = (Button) findViewById(R.id.button_Save);
        nameText = (TextView) findViewById(R.id.editText_Name);
        addressText = (TextView) findViewById(R.id.editText_Address);
        phoneText = (TextView) findViewById(R.id.editText_Phone);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    public void OnButtonSaveClicked(View view) {
        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String phone = phoneText.getText().toString();
        if (stringCount(name) < 1) {
            Toast.makeText(getApplicationContext(), "Insira um nome valido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (actualInfo != null) {
            actualInfo.spinnerIndex = spinner.getSelectedItemPosition();
            actualInfo.name = name;
            actualInfo.address = address;
            actualInfo.phone = phone;
            Toast.makeText(getApplicationContext(), '"' + name + '"' + " foi atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), '"' + name + '"' + " foi criado com sucesso.", Toast.LENGTH_SHORT).show();
            UserManager.getInstance().AddInformationToUser(new Information(name, address, phone, spinner.getSelectedItemPosition()));
        }
        finish();
    }

    private int stringCount(String text) {
        int count = 0;

        //Counts each character except space
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ')
                count++;
        }
        return count;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atençao!");
        builder.setMessage("Voce realmente deseja sair sem salvar suas alteraçoes?");
        builder.setPositiveButton(android.R.string.no, null);
        builder.setNegativeButton(android.R.string.yes,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        finish();
                    }
                });

        builder.create().show();
    }
}