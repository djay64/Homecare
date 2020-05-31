package com.example.homecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainProprietaireActivity extends AppCompatActivity {
    //firebase instances :
    private FirebaseAuth mAuth;
    Button button_inscription;
    Button button_suite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proprietaire);
        mAuth = FirebaseAuth.getInstance();
        button_inscription = (Button) findViewById(R.id.button);
        button_suite = (Button) findViewById(R.id.button1);

        button_inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        button_suite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(MainProprietaireActivity.this, MainActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

    }
}
