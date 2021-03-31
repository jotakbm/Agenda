package com.example.twoscreensgalery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button bttLogIn;
    private Button bttSignIn;
    private Button bttShowPassword;
    private TextView usernameText;
    private TextView passwordText;
    private boolean showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login Screen");
        setupUIViews();
    }

    private void setupUIViews() {
        bttLogIn = (Button) findViewById(R.id.button_LogIn);
        bttSignIn = (Button) findViewById(R.id.button_SignIn);
        bttShowPassword = (Button) findViewById(R.id.button_ShowPass);
        usernameText = (TextView) findViewById(R.id.editText_Username);
        passwordText = (TextView) findViewById(R.id.editText_Password);
    }

    public void OnButtonLogInClicked(View view) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira um username.", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira uma senha.", Toast.LENGTH_SHORT).show();
        } else if (UserManager.getInstance().CheckIfUserExists(username, password)) {
            UserManager.getInstance().LogIn(username, password);
            Intent intent = new Intent(this, Agenda.class);
            startActivity(intent);
        } else
            MakeDialog();
    }

    public void OnButtonSingInClicked(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void OnButtonShowPasswordClicked(View view) {
        showPass = !showPass;
        passwordText.setTransformationMethod((showPass) ? null : new PasswordTransformationMethod());
    }

    private void MakeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atençao!");
        builder.setMessage("Sua conta nao foi encontrada em nosso sistema. Confirme seus dados!");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }


}