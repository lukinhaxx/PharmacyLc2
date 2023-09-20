package com.example.pharmacylc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmacylc.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, senha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ActionBar supportActionBar = getSupportActionBar();
        //if (supportActionBar != null) {
        //  supportActionBar.hide();
        // }
        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);

    }

    public void cadastrar(View view) {
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));

    }

    public void entrar(View view) {

        String userEmail = email.getText().toString();
        String userSenha = senha.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email obrigatório!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userSenha)){
            Toast.makeText(this, "Senha obrigatória!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userSenha.length() < 8){
            Toast.makeText(this, "senha pequena, no minímo 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail, userSenha).addOnCompleteListener(LoginActivity.this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(LoginActivity.this, "Login foi um sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, PharmacyLcActivity.class));

            }else{
                Toast.makeText(LoginActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}