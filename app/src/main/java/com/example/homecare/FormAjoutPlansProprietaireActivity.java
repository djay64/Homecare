package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormAjoutPlansProprietaireActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static FirebaseUser currentUser;

    Button button_ajouter;
    Button button_annuler;
    EditText editText_libelle;
    EditText editText_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajout_plan_proprietaire);

        mAuth = FirebaseAuth.getInstance();

        button_annuler = (Button)findViewById(R.id.btn_annuler_plan_form);
        button_ajouter = (Button)findViewById(R.id.btn_ajouter_plan_form);
        editText_libelle = (EditText)findViewById(R.id.et_libelle_plan_form);
        editText_date = (EditText)findViewById(R.id.et_date_plan_form);

        firebaseDatabase = FirebaseDatabase.getInstance();

        button_ajouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                planInsert();
                FormAjoutPlansProprietaireActivity.this.finish();
            }
        });

        button_annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FormAjoutPlansProprietaireActivity.this.finish();
            }
        });
    }

    private void planInsert() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String idBien = pref.getString("idBien", "0");

        databaseReference = firebaseDatabase.getReference("proprietaire").child(uid).child("bien").child(idBien).child("plan");
        String libelle = editText_libelle.getText().toString();
        String date = editText_date.getText().toString();


        Plan plan = new Plan(libelle, date);

        databaseReference.push().setValue(plan);
    }
}
