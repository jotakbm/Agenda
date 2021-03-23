package com.example.twoscreensgalery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Map<String, String> account = new HashMap<String, String>();
    private Button bttLogIn;
    private Button bttSignIn;
    private TextView usernameText;
    private TextView passwordText;

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
        usernameText = (TextView) findViewById(R.id.editText_Username);
        passwordText = (TextView) findViewById(R.id.editText_Password);
    }

    public void ButtonLogInClicked(View view) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira um username.", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira uma senha.", Toast.LENGTH_SHORT).show();
        } else if (account.containsKey(username) && account.get(username).equals(password)) {
            Intent intent = new Intent(MainActivity.this, Galery.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "Essa conta nao existe, confira seus dados.", Toast.LENGTH_SHORT).show();
    }

    public void ButtonSingInClicked(View view) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira um username.", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira uma senha.", Toast.LENGTH_SHORT).show();
        } else if (account.containsKey(username)) {
            Toast.makeText(getApplicationContext(), "Uma conta com o mesmo username já existe.", Toast.LENGTH_SHORT).show();
        } else {
            account.put(username, password);
            Toast.makeText(getApplicationContext(), "Sua conta foi criada com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }
}