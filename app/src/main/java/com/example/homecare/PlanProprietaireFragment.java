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
import android.widget.AdapterView;
import android.widget.ImageButton;
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


public class PlanProprietaireFragment extends Fragment {
    private FirebaseAuth mAuth;
    ListView listViewPlan;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser currentUser;
    CustomListAdapterPlan adapter;

    ImageButton button_add_alertes;

    public static PlanProprietaireFragment newInstance() {
        return (new PlanProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_proprietaire,
                container, false);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        button_add_alertes = (ImageButton)view.findViewById(R.id.btn_add_plans);

        button_add_alertes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(getActivity(), FormAjoutPlansProprietaireActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

        List<Plan> listPlans = getListData();
        listViewPlan = (ListView) view.findViewById(R.id.listViewPlan);
        adapter = new CustomListAdapterPlan(getActivity().getApplicationContext(), listPlans);
        listViewPlan.setAdapter(adapter);

        // When the user clicks on the ListItem
        listViewPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object p = listViewPlan.getItemAtPosition(position);
                Plan plan = (Plan) p;

//                final Intent acceuilProprietaire = new Intent(getActivity(), MainActivity.class);
//                acceuilProprietaire.putExtra("idPlan", plan.getId());
//                startActivity(acceuilProprietaire);
            }
        });
        return view;
    }

    private  List<Plan> getListData() {
        final List<com.example.homecare.Plan> listPlan = new ArrayList<com.example.homecare.Plan>();

        String uid = currentUser.getUid();

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final String idBien = pref.getString("idBien", "0");

        DatabaseReference user = ref.child("proprietaire").child(uid).child("bien").child(idBien).child("plan");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    com.example.homecare.Plan plan = new com.example.homecare.Plan();
                    plan.setLibelle(snapshot.child("libelle").getValue().toString());
//                    Log.d("adresse",snapshot.child("adresse").getValue().toString());
                    plan.setDate(snapshot.child("date").getValue().toString());
                    plan.setId(snapshot.getKey());
                    Log.d("idPlan",plan.getId());

                    listPlan.add(plan);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listPlan;
    }

}