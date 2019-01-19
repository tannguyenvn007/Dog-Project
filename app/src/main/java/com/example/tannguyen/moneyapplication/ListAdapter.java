package com.example.tannguyen.moneyapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<HistoryAct> mData = new ArrayList<>();
    Actions actions = new Actions();

    public ListAdapter(Context mContext, ArrayList<HistoryAct> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_history_items, parent, false);

        TextView txtAdress = (TextView) rowView.findViewById(R.id.address);
        TextView txtMoney = (TextView) rowView.findViewById(R.id.total_money);
        TextView txtTime = (TextView) rowView.findViewById(R.id.time);

        txtAdress.setText(mData.get(position).getAddress());
        txtMoney.setText(mData.get(position).getMoney()+"");
        txtTime.setText(mData.get(position).getTime()+"");

        return rowView;
    }


    }

