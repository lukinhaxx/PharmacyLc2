package com.example.pharmacylc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    EditText nome, email, senha, senhaa;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //ActionBar supportActionBar = getSupportActionBar();
        //if (supportActionBar != null) {
        //supportActionBar.hide();
        //}
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(CadastroActivity.this, PharmacyLcActivity.class));
            finish();
        }
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        senhaa = findViewById(R.id.senhaa);

        sharedPreferences = getSharedPreferences("Tela de Boas Vindas", MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime", true);

        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();

            Intent intent = new Intent(CadastroActivity.this, TelaDeBoasVindas.class);
            startActivity(intent);
            finish();
        }

    }

    public void cadastrar(View view) {

        String userNome = nome.getText().toString();
        String userEmail = email.getText().toString();
        String userSenha = senha.getText().toString();
        String userSenhaa = senhaa.getText().toString();


        if(TextUtils.isEmpty(userNome)){
            Toast.makeText(this, "Nome obrigatório!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email obrigatório!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userSenha)){
            Toast.makeText(this, "Senha obrigatória!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userSenhaa)){
            Toast.makeText(this, "Senha obrigatória!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userSenha.length() < 8){
            Toast.makeText(this, "senha pequena, no minímo 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userSenhaa.length() < 8){
            Toast.makeText(this, "senha pequena, no minímo 8 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!userSenha.equals(userSenhaa)) {

            Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(userEmail, userSenha).addOnCompleteListener(CadastroActivity.this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Successo a Cadastrar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroActivity.this, PharmacyLcActivity.class));
                } else {
                    Toast.makeText(CadastroActivity.this, "Cadastro Falhou" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void entrar(View view) {
        startActivity(new Intent(CadastroActivity.this,LoginActivity.class));

    }
}