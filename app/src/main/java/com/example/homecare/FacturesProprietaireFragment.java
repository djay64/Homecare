package com.example.homecare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FacturesProprietaireFragment extends Fragment {

    public static FacturesProprietaireFragment newInstance() {
        return (new FacturesProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_factures_proprietaire, container, false);
    }
}