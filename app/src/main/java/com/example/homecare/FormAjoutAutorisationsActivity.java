package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormAjoutAutorisationsActivity extends AppCompatActivity {
    Button button_annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajout_autorisations);
        button_annuler = (Button)findViewById(R.id.btn_annuler_autorisation_form);

        button_annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FormAjoutAutorisationsActivity.this.finish();
            }
        });
    }
}
