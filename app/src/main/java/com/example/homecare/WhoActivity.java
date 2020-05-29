package com.example.homecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WhoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1000;
    Button button_proprietaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_who);

        button_proprietaire = (Button) findViewById(R.id.btn_proprietaire);

        button_proprietaire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(WhoActivity.this, SignInActivity.class);
                startActivityForResult(acceuilProprietaire, REQUEST_CODE);
            }
        });
    }
}
