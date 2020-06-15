package com.example.homecare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainProprietaireActivity extends AppCompatActivity {
    //firebase instances :
    private FirebaseAuth mAuth;
    Button button_accueil;
    ListView listView;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser currentUser;
    CustomListAdapterBien adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proprietaire);
        mAuth = FirebaseAuth.getInstance();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        button_accueil = (Button) findViewById(R.id.btn_accueil);

        button_accueil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(MainProprietaireActivity.this, FormAjoutBiensProprietaireActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

        List<Bien> listBiens = getListData();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapterBien(this, listBiens);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Bien bien = (Bien) o;

                final Intent acceuilProprietaire = new Intent(MainProprietaireActivity.this, MainActivity.class);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("idBien", bien.getId());
                editor.commit();
//                acceuilProprietaire.putExtra("idBien", bien.getId());
                startActivity(acceuilProprietaire);
            }
        });

    }

    private  List<Bien> getListData() {
        final List<Bien> list = new ArrayList<Bien>();

        String uid = currentUser.getUid();
//        Log.d("uid", uid);
        DatabaseReference user = ref.child("proprietaire").child(uid).child("bien");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Bien bien = new Bien();
                    bien.setAdresse(snapshot.child("adresse").getValue().toString());
//                    Log.d("adresse",snapshot.child("adresse").getValue().toString());
                    bien.setVille(snapshot.child("ville").getValue().toString());
                    bien.setId(snapshot.getKey());
//                    Log.d("id",bien.getId());

                    list.add(bien);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}
