package com.example.homecare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InterventionsProprietaireFragment extends Fragment {

    public static InterventionsProprietaireFragment newInstance() {
        return (new InterventionsProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interventions_proprietaire, container, false);
    }
}