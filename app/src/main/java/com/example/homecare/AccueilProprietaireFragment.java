package com.example.homecare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccueilProprietaireFragment extends Fragment {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser currentUser;
    TextView nom;
    TextView prenom;
    TextView adresse;
    TextView ville;

    User connectedUser = new User();

    public static AccueilProprietaireFragment newInstance() {
        return (new AccueilProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil_proprietaire,
                container, false);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        nom = (TextView) view.findViewById(R.id.tv_nom_acceuil);
        prenom = (TextView) view.findViewById(R.id.tv_prenom_acceuil);
        adresse = (TextView) view.findViewById(R.id.tv_adresse_acceuil);
        ville = (TextView) view.findViewById(R.id.tv_ville_acceuil);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        String uid = currentUser.getUid();

//        Bundle extras = this.getActivity().getIntent().getExtras();
//        final String idBien = extras.getString("idBien");
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final String idBien = pref.getString("idBien", "0");


        Log.d("idBien", idBien);

        DatabaseReference user = ref.child("proprietaire").child(uid);
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                connectedUser = dataSnapshot.getValue(User.class);

                nom.setText(connectedUser.getNom());
                prenom.setText(connectedUser.getPrenom());

                Bien bien;
                bien = dataSnapshot.child("bien").child(idBien).getValue(Bien.class);
                adresse.setText(bien.getAdresse());
                ville.setText(bien.getVille());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


