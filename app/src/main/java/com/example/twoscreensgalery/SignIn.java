package com.example.twoscreensgalery;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private Button bttSignIn;
    private TextView usernameText;
    private TextView passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Sign In");
        setupUIViews();
    }
    private void setupUIViews() {
        bttSignIn = (Button) findViewById(R.id.button_SignInSI);
        usernameText = (TextView) findViewById(R.id.editText_UsernameSI);
        passwordText = (TextView) findViewById(R.id.editText_PasswordSI);
    }

    public void ButtonSingInClicked(View view) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira um username.", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Insira uma senha.", Toast.LENGTH_SHORT).show();
        } else if (UserManager.getInstance().CheckIfUsernameExists(username)) {
            Toast.makeText(getApplicationContext(), "Uma conta com o mesmo username já existe.", Toast.LENGTH_SHORT).show();
        } else {
            UserManager.getInstance().CreateNewAccount(username, password);
            Toast.makeText(getApplicationContext(), "Sua conta foi criada com sucesso.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}