package com.example.homecare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilProprietaireFragment extends Fragment {
    ImageButton button_add_biens;
    ImageButton button_choix_biens;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser currentUser;
    TextView nom;
    TextView prenom;
    TextView adresse;
    TextView ville;
    User connectedUser = new User();

    public static ProfilProprietaireFragment newInstance() {
        return (new ProfilProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_proprietaire,
                container, false);
        button_add_biens = (ImageButton)view.findViewById(R.id.btn_add_biens);
        button_choix_biens = (ImageButton)view.findViewById(R.id.btn_choix_bien);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        nom = (TextView) view.findViewById(R.id.tv_nom_profil);
        prenom = (TextView) view.findViewById(R.id.tv_prenom_profil);
        adresse = (TextView) view.findViewById(R.id.tv_adresse_profil);
        ville = (TextView) view.findViewById(R.id.tv_ville_profil);

        button_add_biens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(getActivity(), FormAjoutBiensProprietaireActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

        button_choix_biens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(getActivity(), MainProprietaireActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        String uid = currentUser.getUid();
        Log.d("uid", uid);

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        final String idBien = pref.getString("idBien", "0");
//        Log.d("idBien", idBien);

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