package com.example.homecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapterPlan extends BaseAdapter {

    private List<Plan> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapterPlan(Context aContext, List<Plan> listData) {
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
        CustomListAdapterPlan.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_plan_layout, null);
            holder = new CustomListAdapterPlan.ViewHolder();
            holder.libelle = (TextView) convertView.findViewById(R.id.tv_libelle_list);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date_list);
            convertView.setTag(holder);
        } else {
            holder = (CustomListAdapterPlan.ViewHolder) convertView.getTag();
        }

        Plan plan = this.listData.get(position);
        holder.libelle.setText(plan.getLibelle());
        holder.date.setText("Date: " + plan.getDate());

        return convertView;
    }

    static class ViewHolder {
        TextView libelle;
        TextView date;
    }
}
