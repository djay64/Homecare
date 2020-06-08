package com.example.homecare;

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
    User connectedUser = new User();

    public static AccueilProprietaireFragment newInstance() {
        return (new AccueilProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil_proprietaire,
                container, false);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        nom = (TextView) view.findViewById(R.id.tv_nom);
        prenom = (TextView) view.findViewById(R.id.tv_prenom);


        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        String uid = currentUser.getUid();

        DatabaseReference user = ref.child("proprietaire").child("1");
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               connectedUser = dataSnapshot.getValue(User.class);

               nom.setText(connectedUser.getNom());
               prenom.setText(connectedUser.getPrenom());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}