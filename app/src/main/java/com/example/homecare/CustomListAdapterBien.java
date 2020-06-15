package com.example.homecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapterBien extends BaseAdapter {

    private List<Bien> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapterBien(Context aContext, List<Bien> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_bien_layout, null);
            holder = new ViewHolder();
            holder.adresse = (TextView) convertView.findViewById(R.id.tv_adresse_list);
            holder.ville = (TextView) convertView.findViewById(R.id.tv_ville_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bien bien = this.listData.get(position);
        holder.adresse.setText(bien.getAdresse());
        holder.ville.setText("Ville: " + bien.getVille());

        return convertView;
    }

    static class ViewHolder {
        TextView adresse;
        TextView ville;
    }
}

