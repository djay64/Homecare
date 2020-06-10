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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    //firebase instances :
    private FirebaseAuth mAuth;
    private static final String TAG = "Inscription utilisateur" ;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static FirebaseUser currentUser;

    Button button_inscription;
    EditText editText_nom;
    EditText editText_prenom;
    EditText editText_email;
    EditText editText_mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        button_inscription = (Button) findViewById(R.id.btn_inscription_inscription_from);
        editText_nom = (EditText) findViewById(R.id.et_nom_inscription_form);
        editText_prenom = (EditText) findViewById(R.id.et_prenom_inscription_form);
        editText_email = (EditText) findViewById(R.id.et_email_inscription_form);
        editText_mdp = (EditText) findViewById(R.id.et_mdp_inscription_form);

        firebaseDatabase = FirebaseDatabase.getInstance();

        button_inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount(editText_email.getText().toString(), editText_mdp.getText().toString());
            }
        });
    }

    private void userInsert() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();
        databaseReference = firebaseDatabase.getReference("proprietaire").child(uid);
        String nom = editText_nom.getText().toString();
        String prenom = editText_prenom.getText().toString();

        User user = new User(nom,prenom);

        databaseReference.setValue(user);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void createAccount(String email, String mdp){
        mAuth.createUserWithEmailAndPassword(email, mdp)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userInsert();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            Log.d("Utilisateurs", currentUser.toString());
            final Intent acceuilProprietaire = new Intent(SignInActivity.this, MainProprietaireActivity.class);
            startActivity(acceuilProprietaire);
        }else {
        }
    }
}
