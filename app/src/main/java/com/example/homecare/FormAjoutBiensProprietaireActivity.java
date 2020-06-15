package com.example.homecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormAjoutBiensProprietaireActivity extends AppCompatActivity {
    //firebase instances :
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static FirebaseUser currentUser;

    Button button_ajouter;
    Button button_annuler;
    EditText editText_adresse;
    EditText editText_ville;
    EditText editText_cp;
    EditText editText_date;
    EditText editText_surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajout_biens_proprietaire);

        mAuth = FirebaseAuth.getInstance();

        button_ajouter = (Button) findViewById(R.id.btn_ajouter_bien_form);
        button_annuler = (Button) findViewById(R.id.btn_annuler_bien_form);
        editText_adresse = (EditText) findViewById(R.id.et_adresse_bien_form);
        editText_ville = (EditText) findViewById(R.id.et_ville_bien_form);
        editText_cp = (EditText) findViewById(R.id.et_cp_bien_form);
        editText_date = (EditText) findViewById(R.id.et_date_bien_form);
        editText_surface = (EditText) findViewById(R.id.et_surface_bien_form);

        firebaseDatabase = FirebaseDatabase.getInstance();
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bienInsert();
                FormAjoutBiensProprietaireActivity.this.finish();
            }
        });
        button_annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FormAjoutBiensProprietaireActivity.this.finish();
            }
        });
    }

    private void bienInsert() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();

        databaseReference = firebaseDatabase.getReference("proprietaire").child(uid).child("bien");

        String adresse = editText_adresse.getText().toString();
        String ville = editText_ville.getText().toString();
        String cp = editText_cp.getText().toString();
        String date = editText_date.getText().toString();
        String surface = editText_surface.getText().toString();

        Bien bien = new Bien(adresse, ville, cp, date, surface);

        databaseReference.push().setValue(bien);
    }

}
