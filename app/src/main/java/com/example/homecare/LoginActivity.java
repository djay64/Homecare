package com.example.homecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //firebase instances :
    private FirebaseAuth mAuth;

    public static final int REQUEST_CODE = 2000;
    private static final String TAG = "Connection utilisateur" ;
    Button button_connection;
    Button button_inscription;
    EditText editText_email;
    EditText editText_mdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        button_connection = (Button) findViewById(R.id.btn_connection);
        button_inscription = (Button) findViewById(R.id.btn_inscription);
        editText_email = (EditText) findViewById(R.id.et_email);
        editText_mdp = (EditText) findViewById(R.id.et_mdp);

        button_connection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                signIn(editText_email.getText().toString(), editText_mdp.getText().toString());

            }
        });

        button_inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(LoginActivity.this, WhoActivity.class);
                startActivityForResult(acceuilProprietaire, REQUEST_CODE);
            }
        });

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signIn(String email, String mdp){
        mAuth.signInWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            Log.d("Utilisateurs", currentUser.toString());
            final Intent acceuilProprietaire = new Intent(LoginActivity.this, MainProprietaireActivity.class);
            startActivityForResult(acceuilProprietaire, REQUEST_CODE);
        }else {

        }
    }
}
