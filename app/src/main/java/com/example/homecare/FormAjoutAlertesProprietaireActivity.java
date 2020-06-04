package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FormAjoutAlertesProprietaireActivity extends AppCompatActivity {
Button button_annuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajout_alertes_proprietaire);
        button_annuler = (Button)findViewById(R.id.btn_AnnulerFrom);

        button_annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FormAjoutAlertesProprietaireActivity.this.finish();
            }
        });
    }
}
