package com.example.homecare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class InterventionsProprietaireFragment extends Fragment {
    ImageButton button_add_alertes;

    public static InterventionsProprietaireFragment newInstance() {
        return (new InterventionsProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interventions_proprietaire,
                container, false);
        button_add_alertes = (ImageButton)view.findViewById(R.id.btn_add_interventions);

        button_add_alertes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent acceuilProprietaire = new Intent(getActivity(), FormAjoutInterventionsProprietaireActivity.class);
                startActivity(acceuilProprietaire);
            }
        });

        return view;
    }
}