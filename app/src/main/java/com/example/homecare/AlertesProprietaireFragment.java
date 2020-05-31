package com.example.homecare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AlertesProprietaireFragment extends Fragment {

    public static AlertesProprietaireFragment newInstance() {
        return (new AlertesProprietaireFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alertes_proprietaire, container, false);
    }
}